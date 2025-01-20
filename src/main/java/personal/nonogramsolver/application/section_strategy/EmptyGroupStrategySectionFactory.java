/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import java.util.LinkedList;
import java.util.List;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;

/**
 *
 * @author German at CLEZ
 */
public class EmptyGroupStrategySectionFactory implements StrategySectionFactory {

    @Override
    public StrategySection build() {
        return new EmptyGroupStrategySection();
    }
    
    public class EmptyGroupStrategySection implements StrategySection {

        @Override
        public InformationResult getInformation(GroupSpace space) {
            if (space.group().size() > 0) return new InformationResult(true, List.of());

            List<SectionInformation> info = new LinkedList();
            for (int i = 0; i < space.size(); i++) {
                if (space.val(i) == CellStatus.UNKNOWN) info.add(new SectionInformation(CellStatus.DISABLED, i));
                else if (space.val(i) == CellStatus.ENABLED) return new InformationResult(true, List.of());
            }
            
            return new InformationResult(true, info);
        }
        
    }
    
}
