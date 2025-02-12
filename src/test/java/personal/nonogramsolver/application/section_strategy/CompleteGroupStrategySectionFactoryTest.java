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
import personal.nonogramsolver.testutils.AssertionUtils;

/**
 *
 * @author German at CLEZ
 */
public class CompleteGroupStrategySectionFactoryTest {
    
    @Test
    public void testEmptyGroup() {
        
        StrategySection s = new CompleteGroupStrategySectionFactory().build();
        GroupSpace g = new ArrayGroupSpace(new CellStatus[]{CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN}, 0, new ArrayGroup(new Integer[]{}));
        
        StrategySection.InformationResult result = s.getInformation(g);
        
        Assertions.assertTrue(result.completable());
        Assertions.assertEquals(3, result.information().size());
        
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 0), result.information().get(0));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 1), result.information().get(1));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 2), result.information().get(2));
    }
    
    @Test
    public void testCompleteGroup() {
        
        StrategySection s = new CompleteGroupStrategySectionFactory().build();
        GroupSpace g = new ArrayGroupSpace(new CellStatus[]{CellStatus.UNKNOWN, CellStatus.ENABLED, CellStatus.UNKNOWN, CellStatus.ENABLED, CellStatus.ENABLED, CellStatus.UNKNOWN}, 0, new ArrayGroup(new Integer[]{1, 2}));
        
        StrategySection.InformationResult result = s.getInformation(g);
        
        Assertions.assertTrue(result.completable());
        Assertions.assertEquals(3, result.information().size());
        
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 0), result.information().get(0));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 2), result.information().get(1));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.DISABLED, 5), result.information().get(2));
    }
    
}
