/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.application.GroupOperations;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.SectionOperations;
import personal.nonogramsolver.application.SpaceOperations;
import personal.nonogramsolver.application.section_strategy.StrategySection;
import personal.nonogramsolver.application.section_strategy.StrategySectionFactory;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.Section;

/**
 *
 * @author German at CLEZ
 */
public class StrategyNonogramSectionFactory implements StrategyNonogramFactory {

    private final TreeMap<Integer, List<StrategySectionFactory>> strategyFactoryRegister = new TreeMap();

    @Override
    public StrategyNonogram build() {
        return new StrategyNonogramSection(strategyFactoryRegister.values().stream().flatMap(l -> l.stream()).map(StrategySectionFactory::build).toList());
    }
    
    public void registerStrategyFactory(StrategySectionFactory factory, int priority) {
        List<StrategySectionFactory> factories = strategyFactoryRegister.get(priority);
        
        if (factory == null) {
            factories = new LinkedList<>();
            strategyFactoryRegister.put(priority, factories);
        }
        
        factories.add(factory);
    }

    @RequiredArgsConstructor
    public class StrategyNonogramSection implements StrategyNonogram {
     
        private final List<StrategySection> strategies;
        
        @Override
        public List<NonogramInformation> getInformation(Nonogram nonogram) {
            NonogramOperations ops = new NonogramOperations(nonogram);
            for (int i = 0; i < nonogram.rows(); i++) {
                Section section = ops.rowSection(i);
                iterateAllOptions(section, opt -> {
                    System.out.println(opt);
                });
            }
            
            for (int i = 0; i < nonogram.cols(); i++) {
                Section section = ops.colSection(i);
                iterateAllOptions(section, opt -> {
                    System.out.println(opt);
                });
            }
            
            return List.of();
        }
    }
    


    protected void iterateAllOptions(Section section, Consumer<List<IterateOption>> consumer){
        SectionOperations.RemoveBordersResult noBorders = new SectionOperations(section).removeBorders();
        List<SpaceOperations> spaces = noBorders.section().spaces();
        RecursiveIterationStatus status = new RecursiveIterationStatus(spaces.toArray(size -> new SpaceOperations[size]), 0, new GroupOperations(section.group()), 0, new LinkedList());
        iterateRecursive(status, (result) -> {
            consumer.accept(result.stream().map(io -> new IterateOption(
                    io.space.shiftLocation(noBorders.sectionShift()), 
                    noBorders.groupShift() + io.groupShift, 
                    noBorders.groupShift() + io.groupEnd)
            ).toList());
        });
    }
    
    private void iterateRecursive(RecursiveIterationStatus status, Consumer<List<IterateOption>> foundOption) {
        
        if (status.meIndex >= status.spaces.length) {
            // Most consume all groups. If a group is missing skip
            if (status.groupIndex == status.groups.size()) foundOption.accept(status.consumerStatus);
            return;
        }        
        
        for (int i = status.groupIndex; i <= status.groups.size(); i++) {
            // GroupOperations subGroup = status.groups.subGroup(status.groupIndex, i);
            if (!status.spaces[status.meIndex].admits(status.groups.getGroup(), status.groupIndex, i)) continue;
            status.consumerStatus.push(new IterateOption(status.spaces[status.meIndex], status.groupIndex, i));
            iterateRecursive(new RecursiveIterationStatus(
                    status.spaces, 
                    status.meIndex + 1, 
                    status.groups, 
                    i, 
                    status.consumerStatus
            ), foundOption);
            status.consumerStatus.pop();
        }
    }
    
    private static record RecursiveIterationStatus(
        SpaceOperations[] spaces, 
        int meIndex,
        GroupOperations groups, 
        int groupIndex, 
        LinkedList<IterateOption> consumerStatus
    ) {}
    
    
    
    private static record IterateOption(
        SpaceOperations space,
        int groupShift,
        int groupEnd
    ) {}
    
}
