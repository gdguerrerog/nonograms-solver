/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver;

import org.junit.jupiter.api.Test;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.nonogram_strategy.StrategyNonogramFillCompleteFactory;
import personal.nonogramsolver.application.nonogram_strategy.StrategyNonogramSectionFactory;
import personal.nonogramsolver.application.section_strategy.BorderStrategySectionFactory;
import personal.nonogramsolver.application.section_strategy.EmptyGroupStrategySectionFactory;
import personal.nonogramsolver.application.section_strategy.SizeGroupStrategySectionFactory;
import personal.nonogramsolver.application.solver.StrategyNonogramSolverBuilder;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.infrastructure.reader.FileNonogramReader;
import personal.nonogramsolver.infrastructure.writter.NonogramStringWritter;
import personal.nonogramsolver.infrastructure.writter.NonogramWritter;

/**
 *
 * @author German at CLEZ
 */
public class SolveNonogramsTest {
    
    @Test
    public void solveSmile5X5() {
        
        NonogramWritter<String> defaultWritter = new NonogramStringWritter();
        
        Nonogram smile = new FileNonogramReader("smile_5x5.json").readNonogram();
        System.out.println(defaultWritter.writeNonogram(smile));
        
        NonogramOperations operations = new NonogramOperations(smile);
        operations.clear();
        
        
        personal.nonogramsolver.application.solver.NonogramSolver solver = buildAllStrategySolver();
        solver.solve(smile);
        System.out.println(defaultWritter.writeNonogram(smile));
    }
    
    
    private personal.nonogramsolver.application.solver.NonogramSolver buildAllStrategySolver() {
        StrategyNonogramSolverBuilder snsb = new StrategyNonogramSolverBuilder();
        
        snsb.registerStrategyFactory(new StrategyNonogramFillCompleteFactory(), 0);
        
        
        StrategyNonogramSectionFactory snsf = new StrategyNonogramSectionFactory();
        snsb.registerStrategyFactory(snsf, 1);
        
        snsf.registerStrategyFactory(new EmptyGroupStrategySectionFactory(), 0);
        snsf.registerStrategyFactory(new BorderStrategySectionFactory(), 0);
        snsf.registerStrategyFactory(new SizeGroupStrategySectionFactory(), 2);
        
        return snsb.build();
    }
}
