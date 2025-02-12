/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.testutils;

import org.junit.jupiter.api.Assertions;
import personal.nonogramsolver.application.section_strategy.StrategySection;

/**
 *
 * @author German at CLEZ
 */
public class AssertionUtils {
    public static void assertSectionInformation(StrategySection.SectionInformation expected, StrategySection.SectionInformation actual) {
        Assertions.assertEquals(expected.status(), actual.status());         
        Assertions.assertEquals(expected.index(), actual.index());        
    }
}
