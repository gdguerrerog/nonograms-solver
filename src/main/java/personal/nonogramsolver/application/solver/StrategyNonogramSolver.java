/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.solver;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German at CLEZ
 */
public class StrategyNonogramSolver implements NonogramSolver {

    private TreeMap<Integer, List<StrategyNonogramFactory>> strategyFactoryRegister = new TreeMap();
    
    @Override
    public void solve(Nonogram nonogram) {
        new StrategyNonogramSolverExecution(nonogram).resolve();
    }
    
    public void registerStrategyFactory(StrategyNonogramFactory factory, int priority) {
        strategyFactoryRegister.getOrDefault(priority, new LinkedList()).add(factory);
    }
    
    @RequiredArgsConstructor
    private class StrategyNonogramSolverExecution {
        
        private final Nonogram nonogram;
        
        public void resolve() {
            
        }
        
    }
    
}
