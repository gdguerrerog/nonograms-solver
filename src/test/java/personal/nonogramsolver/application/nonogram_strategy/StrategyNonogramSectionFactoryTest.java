/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.List;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.infrastructure.writter.NonogramStringWritter;
import personal.nonogramsolver.testutils.NonogramProvider;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class StrategyNonogramSectionFactoryTest {
    
    @Test
    public void testIterateAllPossibilities() {
        
        int size = 5;
        Nonogram n = NonogramProvider.get().random(size, size, true);
        for(int i = 0; i < size; i++) {
            n.val(i, i, CellStatus.UNKNOWN);
            n.val(i, 4 - i, CellStatus.UNKNOWN);
        }
        
        System.out.println(new NonogramStringWritter().writeNonogram(n));
        
        StrategyNonogramSectionFactory f = new StrategyNonogramSectionFactory();
        StrategyNonogram s = f.build();
        List<StrategyNonogram.NonogramInformation> info = s.getInformation(n);
    }
    
}
