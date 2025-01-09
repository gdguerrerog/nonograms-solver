/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.infrastructure.reader;

import personal.nonogramsolver.infrastructure.reader.BooleanArrayNonogramReader;
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
        
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.size());
        Assertions.assertArrayEquals(new Integer[]{1}, n.col(0));
        Assertions.assertArrayEquals(new Integer[]{1}, n.row(0));

        n = new BooleanArrayNonogramReader(NonogramProvider.n3x3, false).readNonogram();
        
        Assertions.assertArrayEquals(new Integer[]{3, 3}, n.size());
        
        Assertions.assertArrayEquals(new Integer[]{3}, n.col(0));
        Assertions.assertArrayEquals(new Integer[]{1}, n.col(1));
        Assertions.assertArrayEquals(new Integer[]{3}, n.col(2));
        
        Assertions.assertArrayEquals(new Integer[]{3}, n.row(0));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.row(1));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, n.row(2));
        
    }
}
