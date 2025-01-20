/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.ArrayGroup;
import personal.nonogramsolver.domain.ArrayGroupSpace;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;

/**
 *
 * @author German at CLEZ
 */
public class EmptyGroupStrategySectionFactoryTest {
    
    @Test
    public void testEmptyGroup() {
        
        StrategySection s = new EmptyGroupStrategySectionFactory().build();
        GroupSpace g = new ArrayGroupSpace(new CellStatus[]{CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN}, 0, new ArrayGroup(new Integer[]{}));
        
        StrategySection.InformationResult result = s.getInformation(g);
        
        Assertions.assertTrue(result.completable());
        Assertions.assertEquals(3, result.information().size());
        
        Assertions.assertEquals(CellStatus.DISABLED, result.information().get(0).status());         
        Assertions.assertEquals(0, result.information().get(0).index());        
       
        Assertions.assertEquals(CellStatus.DISABLED, result.information().get(1).status());         
        Assertions.assertEquals(1, result.information().get(1).index());  
        
        Assertions.assertEquals(CellStatus.DISABLED, result.information().get(2).status());         
        Assertions.assertEquals(2, result.information().get(2).index());  


    }
    
}
