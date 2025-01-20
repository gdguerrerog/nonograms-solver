/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.solver;

import personal.nonogramsolver.application.nonogram_strategy.StrategyNonogramFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.nonogram_strategy.StrategyNonogram;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.ReadOnlyNonogram;

/**
 *
 * @author German at CLEZ
 */
public class StrategyNonogramSolverBuilder implements NonogramSolverBuilder {
    
    private final TreeMap<Integer, List<StrategyNonogramFactory>> strategyFactoryRegister = new TreeMap();

    public final void registerStrategyFactory(StrategyNonogramFactory factory, int priority) {
        
        List<StrategyNonogramFactory> factories = strategyFactoryRegister.get(priority);
        if (factories == null) strategyFactoryRegister.put(priority, factories = new LinkedList());
        factories.add(factory);
    }

    @Override
    public NonogramSolver build() {
        return new StrategyNonogramSolver();
    }
    
    public class StrategyNonogramSolver implements NonogramSolver {
        @Override
        public void solve(Nonogram nonogram) {
            List<StrategyNonogram> strategies = strategyFactoryRegister.values().stream().flatMap(list -> list.stream()).map(StrategyNonogramFactory::build).toList();
            new StrategyNonogramSolverExecution(nonogram, strategies).resolve();
        }
    }
    
    private static class StrategyNonogramSolverExecution {
        
        private final Nonogram n;
        private final ReadOnlyNonogram ron;
        private final NonogramOperations no;
        private final List<StrategyNonogram> s; // Ordered Strategies
        
        StrategyNonogramSolverExecution(Nonogram n, List<StrategyNonogram> s) {
            this.n = n;
            this.ron = new ReadOnlyNonogram(n);
            this.no = new NonogramOperations(n);
            this.s = s;
        }
        
        public void resolve() {
            
            boolean foundInformation;
            do{
                foundInformation = false;
                for (StrategyNonogram st: s) {
                    List<StrategyNonogram.NonogramInformation> info = st.getInformation(ron);
                    if (info.isEmpty()) continue;
                    
                    foundInformation = true;
                    for (StrategyNonogram.NonogramInformation infoCell: info) {
                        n.val(infoCell.x(), infoCell.y(), infoCell.status());
                    }
                            
                    break;
                }
            } while(foundInformation);
            
            
        }
        
    }
    
}
