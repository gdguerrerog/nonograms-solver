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
        
        Assertions.assertEquals(1, spaces.get(0).startIndex());
        Assertions.assertArrayEquals(new CellStatus[]{UNKNOWN, ENABLED, UNKNOWN}, spaces.get(0).values());
        
        
        execution = new SplitSectionExection(new ArraySection(new Integer[0], new CellStatus[]{UNKNOWN, DISABLED, ENABLED, DISABLED, UNKNOWN, ENABLED, DISABLED, UNKNOWN}));
        spaces = execution.getSpaces();

        Assertions.assertEquals(3, spaces.size());
        
        Assertions.assertEquals(0, spaces.get(0).startIndex());
        Assertions.assertArrayEquals(new CellStatus[]{UNKNOWN}, spaces.get(0).values());

        Assertions.assertEquals(4, spaces.get(1).startIndex());
        Assertions.assertArrayEquals(new CellStatus[]{UNKNOWN, ENABLED}, spaces.get(1).values());

        
        Assertions.assertEquals(7, spaces.get(2).startIndex());
        Assertions.assertArrayEquals(new CellStatus[]{UNKNOWN}, spaces.get(2).values());

    }
    
}
