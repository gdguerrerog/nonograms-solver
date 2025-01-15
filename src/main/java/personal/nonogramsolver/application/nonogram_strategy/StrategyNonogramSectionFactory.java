/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.application.GroupOperations;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.section_strategy.StrategySection;
import personal.nonogramsolver.application.section_strategy.StrategySectionFactory;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.DISABLED;
import static personal.nonogramsolver.domain.CellStatus.ENABLED;
import static personal.nonogramsolver.domain.CellStatus.UNKNOWN;
import personal.nonogramsolver.domain.Group;
import personal.nonogramsolver.domain.IllegalNonogramStatusException;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.SectionAFD;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 *
 * @author German at CLEZ
 */
public class StrategyNonogramSectionFactory implements StrategyNonogramFactory {

    private final TreeMap<Integer, List<StrategySectionFactory>> strategyFactoryRegister = new TreeMap();

    @Override
    public StrategyNonogram build() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void registerStrategyFactory(StrategySectionFactory factory, int priority) {
        strategyFactoryRegister.getOrDefault(priority, new LinkedList()).add(factory);
    }

    @RequiredArgsConstructor
    public class StrategyNonogramSection implements StrategyNonogram {
     
        private final List<StrategySection> strategies;
        
        @Override
        public List<NonogramInformation> getInformation(Nonogram nonogram) {
            NonogramOperations ops = new NonogramOperations(nonogram);
            for (int i = 0; i < nonogram.rows(); i++) {
            }
            
            return List.of();
        }
    }
    


    protected void iterateAllOptions(Section section, Consumer<Tuple2<SectionSpace, Integer[]>> consumer){
        List<SectionSpace> spaces = getSpaces(section);
//        Tuple2<List<SectionSpace>, Integer[]> sectionWithNoBorders = removeBorders(spaces, section.grops());
    }
    
    private void iterateRecursive(RecursiveIterationStatus status) {
        
        if (status.meIndex >= status.spaces.length) {
            // Most consume all groups. If a group is missing skip
            if (status.groupIndex == status.groups.size()) status.consumer.accept(status.consumerStatus);
            return;
        }        
        
        for (int i = status.groupIndex; i < status.groups.size(); i++) {
            status.consumerStatus.push(Tuples.of(status.spaces[status.meIndex], new Integer[]{status.groupIndex, i}));
            iterateRecursive(new RecursiveIterationStatus(
                    status.spaces, 
                    status.meIndex + 1, 
                    status.groups, 
                    i, 
                    status.consumerStatus, 
                    status.consumer
            ));
            status.consumerStatus.pop();
        }
    }
    
    protected List<SectionSpace> getSpaces(Section section) {
        return List.of();
    }

    
    protected static record SectionSpace(
        int startIndex,
        CellStatus[] values,
        boolean completed
    ){
        public boolean admits(Group group, int startIndex, int endIndex) {
            if (completed) {
                if (endIndex - startIndex != 1) return false;
                return group[startIndex] == values.length;
            }
            
            return values.length >= new GroupOperations(group).minSize();
        }

    }
    

    
    private static record RecursiveIterationStatus(
        SectionSpace[] spaces, 
        int meIndex,
        GroupOperations groups, 
        int groupIndex, 
        LinkedList<Tuple2<SectionSpace, Integer[]>> consumerStatus, 
        Consumer<List<Tuple2<SectionSpace, Integer[]>>> consumer
    ) {}
    
}
