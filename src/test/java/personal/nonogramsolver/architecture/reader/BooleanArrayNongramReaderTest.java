/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.architecture.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.testutils.NonogramProvider;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class BooleanArrayNongramReaderTest {
 
    
    @Test
    public void testString() {
        Nonogram n = new BooleanArrayNonogramReader(NonogramProvider.n1x1, false).readNonogram();
        
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.getSize());
        Assertions.assertArrayEquals(new Integer[]{1}, n.getColInfo(0));
        Assertions.assertArrayEquals(new Integer[]{1}, n.getRowInfo(0));

        n = new BooleanArrayNonogramReader(NonogramProvider.n3x3, false).readNonogram();
        
        Assertions.assertArrayEquals(new Integer[]{3, 3}, n.getSize());
        
        Assertions.assertArrayEquals(new Integer[]{3}, n.getColInfo(0));
        Assertions.assertArrayEquals(new Integer[]{1}, n.getColInfo(1));
        Assertions.assertArrayEquals(new Integer[]{3}, n.getColInfo(2));
        
        Assertions.assertArrayEquals(new Integer[]{3}, n.getRowInfo(0));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.getRowInfo(1));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.getRowInfo(2));
        
    }
}
