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
public class SizeGroupStrategySectionFactoryTest {
    
    
    @Test
    public void testSizeGroup() {
        StrategySection s = new SizeGroupStrategySectionFactory().build();
        GroupSpace g = new ArrayGroupSpace(new CellStatus[]{CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN}, 0, new ArrayGroup(new Integer[]{2, 2}));

        StrategySection.InformationResult result =  s.getInformation(g);
        Assertions.assertTrue(result.completable());
        
        Assertions.assertEquals(4, result.information().size());
        
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 0), result.information().get(0));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 1), result.information().get(1));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 3), result.information().get(2));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 4), result.information().get(3));
    }
    
    @Test
    public void testSizeGroup2() {
        StrategySection s = new SizeGroupStrategySectionFactory().build();
        GroupSpace g = new ArrayGroupSpace(new CellStatus[]{CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN, CellStatus.UNKNOWN}, 0, new ArrayGroup(new Integer[]{2, 2}));

        StrategySection.InformationResult result =  s.getInformation(g);
        Assertions.assertTrue(result.completable());
        
        Assertions.assertEquals(2, result.information().size());
        
        
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 1), result.information().get(0));
        AssertionUtils.assertSectionInformation(new StrategySection.SectionInformation(CellStatus.ENABLED, 4), result.information().get(1));
    }
    
}
