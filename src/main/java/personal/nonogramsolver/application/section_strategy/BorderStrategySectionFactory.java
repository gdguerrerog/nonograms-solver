/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;
import personal.nonogramsolver.domain.IllegalNonogramStatusException;

/**
 *
 * @author German at CLEZ
 */
public class BorderStrategySectionFactory implements StrategySectionFactory {

    @Override
    public StrategySection build() {
        return new BorderStrategySection();
    }
    
    public class BorderStrategySection implements StrategySection {

        @Override
        public InformationResult getInformation(GroupSpace space) {
            if (space.group().size() == 0) return new InformationResult(true, List.of());
            
            
            Map<Integer, CellStatus> info = new TreeMap();
            if (space.val(0).equals(CellStatus.ENABLED)) {
                for (int i = 1; i < space.group().val(0); i++) {
                    if (space.val(i) == CellStatus.UNKNOWN) info.put(i, CellStatus.ENABLED);
                    else if (space.val(i) == CellStatus.DISABLED) return new InformationResult(false, null);
                }
                if (space.group().val(0) < space.size() && space.val(space.group().val(0)) == CellStatus.UNKNOWN) info.put(space.group().val(0), CellStatus.DISABLED);
            }
            
            int lastGroup = space.group().val(space.group().size() - 1);
            if (space.val(space.size() - 1).equals(CellStatus.ENABLED)) {
                for (int i = 1; i < lastGroup; i++) {
                    int currentIndex = space.size() - i - 1;
                    if (space.val(currentIndex) == CellStatus.UNKNOWN) info.put(currentIndex, CellStatus.ENABLED);
                    else if (space.val(currentIndex) == CellStatus.DISABLED) return new InformationResult(false, null);
                }
                if (lastGroup < space.size() && space.val(space.size() - lastGroup - 1) == CellStatus.UNKNOWN) info.put(space.size() - lastGroup - 1, CellStatus.DISABLED);
            }
            
            
            return new InformationResult(true, info.entrySet().stream().map(e -> new SectionInformation(e.getValue(), e.getKey())).toList()); 
        }
        
        
    }
}
