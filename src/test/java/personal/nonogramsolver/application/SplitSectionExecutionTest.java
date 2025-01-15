/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.*;
import reactor.util.function.Tuple2;

/**
 *
 * @author German at CLEZ
 */
public class SplitSectionExecutionTest {

    @Test
    public void testSpaces() {
        SplitSectionExection execution = new SplitSectionExection(new ArraySection(new Integer[0], new CellStatus[]{DISABLED, UNKNOWN, ENABLED, UNKNOWN, DISABLED}));
        List<SplitSectionExection.SectionSpace> spaces = execution.getSpaces();
        
        Assertions.assertEquals(1, spaces.size());
        
        assertSectionSpace(new SplitSectionExection.SectionSpace(1, new CellStatus[]{UNKNOWN, ENABLED, UNKNOWN}, false), spaces.get(0));
        
        execution = new SplitSectionExection(new ArraySection(new Integer[0], new CellStatus[]{UNKNOWN, DISABLED, ENABLED, ENABLED, DISABLED, UNKNOWN, ENABLED, DISABLED, UNKNOWN}));
        spaces = execution.getSpaces();

        Assertions.assertEquals(4, spaces.size());
        
        assertSectionSpace(new SplitSectionExection.SectionSpace(0, new CellStatus[]{UNKNOWN}, false), spaces.get(0));
        assertSectionSpace(new SplitSectionExection.SectionSpace(2, new CellStatus[]{ENABLED, ENABLED}, true), spaces.get(1));
        assertSectionSpace(new SplitSectionExection.SectionSpace(5, new CellStatus[]{UNKNOWN, ENABLED}, false), spaces.get(2));
        assertSectionSpace(new SplitSectionExection.SectionSpace(8, new CellStatus[]{UNKNOWN}, false), spaces.get(3));

    }
    
    private void assertSectionSpace(SplitSectionExection.SectionSpace expected, SplitSectionExection.SectionSpace current) {
        Assertions.assertEquals(expected.startIndex(), current.startIndex());
        Assertions.assertArrayEquals(expected.values(), current.values());
        Assertions.assertEquals(expected.completed(), current.completed());
    }
    
    @Test
    public void testRemoveBorders() {
        SplitSectionExection execution = new SplitSectionExection(new ArraySection(new Integer[0], new CellStatus[]{DISABLED, ENABLED, DISABLED, UNKNOWN, ENABLED, DISABLED, ENABLED, DISABLED, ENABLED}));
        List<SplitSectionExection.SectionSpace> spaces = execution.getSpaces();
        Integer[] groups = {1, 2, 1, 1};
        
        Tuple2<List<SplitSectionExection.SectionSpace>, Integer[]> result = SplitSectionExection.removeBorders(spaces, groups);
        
        Assertions.assertEquals(1, result.getT1().size());
        
        assertSectionSpace(new SplitSectionExection.SectionSpace(3, new CellStatus[]{UNKNOWN, ENABLED}, false), result.getT1().get(0));
        Assertions.assertArrayEquals(new Integer[]{2}, result.getT2());

    }
    
}
