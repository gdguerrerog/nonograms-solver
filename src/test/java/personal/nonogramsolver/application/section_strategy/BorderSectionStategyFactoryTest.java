/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import org.junit.jupiter.api.Test;
import personal.nonogramsolver.application.nonogram_strategy.StrategyNonogramSectionFactory;
import personal.nonogramsolver.application.solver.StrategyNonogramSolverBuilder;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.infrastructure.reader.BooleanArrayNonogramReader;
import personal.nonogramsolver.infrastructure.writter.NonogramStringWritter;

/**
 *
 * @author German at CLEZ
 */
public class BorderSectionStategyFactoryTest {

    @Test
    public void testBorders() {
//        Nonogram n = new BooleanArrayNonogramReader(new Boolean[][]{
//            {true, false, false, false},
//            {true, true, true, false},
//            {false, true, true, false},
//            {false, false, false, true},
//            {false, false, true, true}
//        }, false).readNonogram();

        Nonogram n = new BooleanArrayNonogramReader(new Boolean[][]{{true, true, true, false, true, true}}, false).readNonogram();
        n.val(0, 0, CellStatus.ENABLED);
        n.val(5, 0, CellStatus.ENABLED);
        
        StrategyNonogramSectionFactory ssnf = new StrategyNonogramSectionFactory();
        ssnf.registerStrategyFactory(new BorderStrategySectionFactory(), 0);

        StrategyNonogramSolverBuilder snsb = new StrategyNonogramSolverBuilder();
        snsb.registerStrategyFactory(ssnf, 0);
        
        snsb.build().solve(n);
        
        System.out.println(new NonogramStringWritter().writeNonogram(n));

    }
}
