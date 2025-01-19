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
        public List<SectionInformation> getInformation(GroupSpace space) {
            
            if (space.group().size() == 0) return IntStream.range(0, space.size()).mapToObj(i -> new SectionInformation(CellStatus.DISABLED, i)).toList();

            Map<Integer, CellStatus> info = new TreeMap();
            if (space.val(0).equals(CellStatus.ENABLED)) {
                for (int i = 1; i < space.group().val(0); i++) {
                    if (space.val(i) == CellStatus.UNKNOWN) info.put(i, CellStatus.ENABLED);
                    else if (space.val(i) == CellStatus.DISABLED) throw new IllegalNonogramStatusException("Border impossible to complete");
                }
            }
            
            if (space.val(space.size() - 1).equals(CellStatus.ENABLED)) {
                for (int i = 1; i < space.group().val(space.size() - 1); i++) {
                    if (space.val(space.size() - i - 1) == CellStatus.UNKNOWN) info.put(space.size() - i - 1, CellStatus.ENABLED);
                    else if (space.val(i) == CellStatus.DISABLED) throw new IllegalNonogramStatusException("Border impossible to complete");
                }
            }
            
            return info.entrySet().stream().map(e -> new SectionInformation(e.getValue(), e.getKey())).toList();
        }
        
        
    }
}
