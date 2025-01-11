/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.application.section_strategy.StrategySection;
import personal.nonogramsolver.application.section_strategy.StrategySectionFactory;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;

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
            return List.of();
        }


    }
    
}
