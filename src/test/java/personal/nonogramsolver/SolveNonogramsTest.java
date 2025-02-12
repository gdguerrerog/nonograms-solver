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
import personal.nonogramsolver.application.section_strategy.CompleteGroupStrategySectionFactory;
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
        solveNonogram("smile_5x5.json");
    }
    
    @Test
    public void solveAirplane5X5() {
        solveNonogram("airplane_5x5.json");
    }
    
    @Test
    public void solveSaltAndPepper10x10() {
        // TODO is bugged
        solveNonogram("salt_&_pepper_10x10.json");
    }
    
    @Test
    public void solveLeaf10x10() {
        solveNonogram("leaf_10x10.json");
    }
    
    
    private void solveNonogram(String name) {
        Nonogram n = new FileNonogramReader(name).readNonogram();
        NonogramWritter<String> defaultWritter = new NonogramStringWritter();
        System.out.println(defaultWritter.writeNonogram(n));
        
        NonogramOperations operations = new NonogramOperations(n);
        operations.clear();
        
        personal.nonogramsolver.application.solver.NonogramSolver solver = buildAllStrategySolver();
        solver.solve(n);
        System.out.println(defaultWritter.writeNonogram(n));
    }
    
    private personal.nonogramsolver.application.solver.NonogramSolver buildAllStrategySolver() {
        StrategyNonogramSolverBuilder snsb = new StrategyNonogramSolverBuilder();
        
        snsb.registerStrategyFactory(new StrategyNonogramFillCompleteFactory(), 0);
        
        
        StrategyNonogramSectionFactory snsf = new StrategyNonogramSectionFactory();
        snsb.registerStrategyFactory(snsf, 1);
        
        snsf.registerStrategyFactory(new CompleteGroupStrategySectionFactory(), 0);
        snsf.registerStrategyFactory(new BorderStrategySectionFactory(), 0);
        snsf.registerStrategyFactory(new SizeGroupStrategySectionFactory(), 2);
        
        return snsb.build();
    }
}
