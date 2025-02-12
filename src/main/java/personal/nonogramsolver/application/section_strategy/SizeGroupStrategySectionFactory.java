/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import java.util.LinkedList;
import java.util.List;
import personal.nonogramsolver.application.GroupOperations;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;

/**
 *
 * @author German at CLEZ
 */
public class SizeGroupStrategySectionFactory implements StrategySectionFactory {

    @Override
    public StrategySection build() {
        return new SizeGroupStrategySection();
    }
    
    public class SizeGroupStrategySection implements StrategySection {

        @Override
        public InformationResult getInformation(GroupSpace space) {
            GroupOperations groupOperations = new GroupOperations(space.group());
            
            int minSize = groupOperations.minSize();
            if (space.size() < minSize) return new InformationResult(false, List.of());
            else {
                List<SectionInformation> info = new LinkedList();
                final int minShift = space.size() - minSize;
                int currentSpaceIndex = 0;
                for (Integer groupElement: space.group()) {
                    for (int i = 0; i + minShift < groupElement; i++) {
                        int currentCell = currentSpaceIndex + minShift + i;
                        if (space.val(currentCell) == CellStatus.DISABLED) return new InformationResult(false, List.of());
                        if (space.val(currentCell) == CellStatus.ENABLED) continue;
                        info.add(new SectionInformation(CellStatus.ENABLED, currentCell));
                    }
                    
                    currentSpaceIndex += groupElement + 1;
                }
                return new InformationResult(true, info);
            }
            
        }
        
    }
    
}
