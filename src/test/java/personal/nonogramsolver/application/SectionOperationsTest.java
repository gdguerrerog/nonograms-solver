/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.ArraySpace;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.DISABLED;
import static personal.nonogramsolver.domain.CellStatus.ENABLED;
import static personal.nonogramsolver.domain.CellStatus.UNKNOWN;
import personal.nonogramsolver.domain.Group;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.Space;

/**
 *
 * @author German at CLEZ
 */
public class SectionOperationsTest {
    
    @Test
    public void testSpaces() {
        SectionOperations ops = new SectionOperations(new ArraySection(new Integer[]{}, new CellStatus[]{DISABLED, UNKNOWN, ENABLED, UNKNOWN, DISABLED}));
        
        List<SpaceOperations> spaces = ops.spaces();
        
        Assertions.assertEquals(1, spaces.size());
        
        assertSpace(new ArraySpace(new CellStatus[]{UNKNOWN, ENABLED, UNKNOWN}, 1), spaces.get(0).getSpace());
        
        ops = new SectionOperations(new ArraySection(new Integer[0], new CellStatus[]{UNKNOWN, DISABLED, ENABLED, ENABLED, DISABLED, UNKNOWN, ENABLED, DISABLED, UNKNOWN}));
        spaces = ops.spaces();

        Assertions.assertEquals(4, spaces.size());
        
        assertSpace(new ArraySpace(new CellStatus[]{UNKNOWN}, 0), spaces.get(0).getSpace());
        assertSpace(new ArraySpace(new CellStatus[]{ENABLED, ENABLED}, 2), spaces.get(1).getSpace());
        assertSpace(new ArraySpace(new CellStatus[]{UNKNOWN, ENABLED}, 5), spaces.get(2).getSpace());
        assertSpace(new ArraySpace(new CellStatus[]{UNKNOWN}, 8), spaces.get(3).getSpace());
    }
    
    @Test
    public void testRemoveBorders() {
        SectionOperations ops = new SectionOperations(new ArraySection(new Integer[]{1, 2, 1, 1}, new CellStatus[]{DISABLED, ENABLED, DISABLED, UNKNOWN, ENABLED, DISABLED, ENABLED, DISABLED, ENABLED}));
        SectionOperations.RemoveBordersResult result = ops.removeBorders();
        
        Assertions.assertEquals(3, result.sectionShift());
        Assertions.assertEquals(3, result.sectionShift());
        assertSection(new ArraySection(new Integer[]{2}, new CellStatus[]{UNKNOWN, ENABLED}), result.section().getSection());
    }
    
    public static void assertSpace(Space expected, Space current) {
        Assertions.assertArrayEquals(new SpaceOperations(expected).statuses(), new SpaceOperations(current).statuses());
        Assertions.assertEquals(expected.compleated(), current.compleated());
        Assertions.assertEquals(expected.sectionIdx(), current.sectionIdx());
    }
    
    public static void assertSection(Section expected, Section current) {
        assertGroup(expected.group(), current.group());
        Assertions.assertArrayEquals(new SectionOperations(expected).values(), new SectionOperations(current).values());

    }
    
    public static void assertGroup(Group expected, Group current) {
        Assertions.assertArrayEquals(new GroupOperations(expected).values(), new GroupOperations(current).values());
    }
}
