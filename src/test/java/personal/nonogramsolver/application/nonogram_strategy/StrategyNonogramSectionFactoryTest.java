/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.List;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.ArrayNonogram;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.infrastructure.reader.BooleanArrayNonogramReader;
import personal.nonogramsolver.infrastructure.writter.NonogramStringWritter;
import personal.nonogramsolver.testutils.NonogramProvider;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class StrategyNonogramSectionFactoryTest {
    
    @Test
    public void testIterateAllPossibilities() {
        
        Nonogram n = new BooleanArrayNonogramReader(new Boolean[][]{{true, false, true, true, false, true, false}}, false).readNonogram();
        n.val(0, 0, CellStatus.ENABLED);
        n.val(1, 0, CellStatus.DISABLED);
        n.val(4, 0, CellStatus.DISABLED);
//        n.val(6, 0, CellStatus.DISABLED);
        
        new StrategyNonogramSectionFactory().build().getInformation(n);
    }
    
}
