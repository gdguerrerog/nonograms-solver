/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.application.GroupOperations;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.SectionOperations;
import personal.nonogramsolver.application.SpaceOperations;
import personal.nonogramsolver.application.section_strategy.StrategySection;
import personal.nonogramsolver.application.section_strategy.StrategySectionFactory;
import personal.nonogramsolver.domain.ArrayGroupSpace;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;
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
        
        if (factories == null) {
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
            
            List<NonogramInformation> nonogramInfo = new LinkedList();
           
            NonogramOperations ops = new NonogramOperations(nonogram);
            for (StrategySection ss: strategies) {
                for (int i = 0; i < nonogram.rows(); i++) {
                    Section section = ops.rowSection(i);
                    Map<Integer, CellStatus> info = sectionInformation(section, ss);
                    for (Map.Entry<Integer, CellStatus> entry: info.entrySet()) {
                        nonogramInfo.add(new NonogramInformation(entry.getValue(), entry.getKey(), i));
                    }
                }
                
                for (int i = 0; i < nonogram.cols(); i++) {
                    Section section = ops.colSection(i);
                    Map<Integer, CellStatus> info = sectionInformation(section, ss);
                    for (Map.Entry<Integer, CellStatus> entry: info.entrySet()) {
                        nonogramInfo.add(new NonogramInformation(entry.getValue(), i, entry.getKey()));
                    }
                }
            }
            
            
            
            return nonogramInfo;
        }
    }
    
    private Map<Integer, CellStatus> sectionInformation(Section section, StrategySection ss) {
        Map<Integer, CellStatus> repeatedInfo = new TreeMap();
        boolean[] firstIteration = {true};
        iterateAllOptions(section, opts -> {
            
            for (IterateOption opt: opts) {
                if (opt.space().getSpace().compleated()) continue;
                
                GroupOperations subGroup = new GroupOperations(section.group()).subGroup(opt.groupShift, opt.groupEnd);
                GroupSpace gs = new ArrayGroupSpace(opt.space().statuses(), opt.space.getSpace().sectionIdx(), subGroup.getGroup());
                StrategySection.InformationResult result = ss.getInformation(gs);
                
                if (!result.completable()) break;
                
                List<StrategySection.SectionInformation> information = result.information();
                for (StrategySection.SectionInformation info: information) {
                    if (firstIteration[0]) repeatedInfo.put(info.index() + gs.sectionIdx(), info.status());
                    else if (repeatedInfo.containsKey(info.index()) && repeatedInfo.get(info.index()) != info.status()) repeatedInfo.remove(info.index());
                }    
            }
            
            firstIteration[0] = false;
        });
        
        return repeatedInfo;
    }
    


    protected void iterateAllOptions(Section section, Consumer<List<IterateOption>> consumer){
        SectionOperations.RemoveBordersResult noBorders = new SectionOperations(section).removeBorders();
        List<SpaceOperations> spaces = noBorders.section().spaces();
        RecursiveIterationStatus status = new RecursiveIterationStatus(spaces.toArray(size -> new SpaceOperations[size]), 0, new GroupOperations(section.group()), noBorders.groupShift(), new LinkedList());
        iterateRecursive(status, (result) -> {
            consumer.accept(result.stream().map(io -> new IterateOption(
                    io.space.shiftLocation(noBorders.sectionShift()), 
                    io.groupShift, 
                    io.groupEnd)
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
            if (!status.spaces[status.meIndex].admits(status.groups.subGroup(status.groupIndex, i).getGroup())) continue;
            status.consumerStatus.addLast(new IterateOption(status.spaces[status.meIndex], status.groupIndex, i));
            iterateRecursive(new RecursiveIterationStatus(
                    status.spaces, 
                    status.meIndex + 1, 
                    status.groups, 
                    i, 
                    status.consumerStatus
            ), foundOption);
            status.consumerStatus.removeLast();
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
