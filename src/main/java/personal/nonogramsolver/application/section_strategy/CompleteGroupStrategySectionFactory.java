/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import java.util.LinkedList;
import java.util.List;
import personal.nonogramsolver.application.GroupSpaceOperations;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;
import personal.nonogramsolver.domain.SectionAFD;

/**
 *
 * @author German at CLEZ
 */
public class CompleteGroupStrategySectionFactory implements StrategySectionFactory {

    @Override
    public StrategySection build() {
        return new CompleteGroupStrategySection();
    }
    
    public class CompleteGroupStrategySection implements StrategySection {

        @Override
        public InformationResult getInformation(GroupSpace space) {
            
            if (space.group().size() == 0) return emptyGroupInformation(space);
            
            final GroupSpaceOperations operations = new GroupSpaceOperations(space);
            
            final int READING_GROUP = 0, NOT_READING_GROUP = 1;
            SectionAFD.IterateResult<CompleteGroupStrategy> result = new SectionAFD<CompleteGroupStrategy>(operations.section().getSection(), NOT_READING_GROUP, (data) -> {
                switch (data.status()) {
                    case READING_GROUP: return switch (data.cell()) {
                        case ENABLED -> new SectionAFD.EvaluateResult(READING_GROUP, new CompleteGroupStrategy(data.acc().groupCount() + 1, true));
                        case DISABLED, UNKNOWN -> {
                            if (data.group() != data.acc().groupCount()) yield new SectionAFD.EvaluateResult(NOT_READING_GROUP, false, new CompleteGroupStrategy(0, false), true);
                            yield new SectionAFD.EvaluateResult(NOT_READING_GROUP, true, new CompleteGroupStrategy(0, true), false);
                        }
                    };
                    case NOT_READING_GROUP: return switch (data.cell()) {
                        case ENABLED -> new SectionAFD.EvaluateResult(READING_GROUP, new CompleteGroupStrategy(data.acc().groupCount() + 1, true));
                        case DISABLED, UNKNOWN -> new SectionAFD.EvaluateResult(NOT_READING_GROUP, data.acc());
                    };
                }
                return null;
            }, new CompleteGroupStrategy(0, true)).setEvaluateLast(true).iterate();
            
            if (result.terminated()) return new InformationResult(true, List.of());
            if (!result.acc().complete()) return new InformationResult(true, List.of());
            if (result.groupIndex() != space.group().size()) return new InformationResult(true, List.of());
            
            List<SectionInformation> info = new LinkedList();
            for (int i = 0; i < space.size(); i++) {
                if (space.val(i) == CellStatus.UNKNOWN) info.add(new SectionInformation(CellStatus.DISABLED, i));
            }
            
            return new InformationResult(true, info);
        }
        
        public InformationResult emptyGroupInformation(GroupSpace space) {
            List<SectionInformation> info = new LinkedList();
            for (int i = 0; i < space.size(); i++) {
                if (space.val(i) == CellStatus.UNKNOWN) info.add(new SectionInformation(CellStatus.DISABLED, i));
                else if (space.val(i) == CellStatus.ENABLED) return new InformationResult(true, List.of());
            }
            
            return new InformationResult(true, info);
        }
    }
    
    private record CompleteGroupStrategy(
        int groupCount, 
        boolean complete
    ){}
    
}
