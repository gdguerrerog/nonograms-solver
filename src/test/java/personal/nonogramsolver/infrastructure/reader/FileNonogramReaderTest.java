/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.infrastructure.reader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.infrastructure.reader.BooleanArrayNonogramReader;
import personal.nonogramsolver.infrastructure.reader.FileNonogramReader;

/**
 *
 * @author German at CLEZ
 */
public class FileNonogramReaderTest {
    
    @Test
    public void readFileTest() {
        FileNonogramReader reader = new FileNonogramReader("smile_5x5.json");
        Nonogram nonogram = reader.readNonogram();

        Boolean[][] smileArr = {
            {true, true, false, true, true},
            {true, true, false, true, true},
            {false, false, false, false, false},
            {true, false, false, false, true},
            {false, true, true, true, false},
        };
        
        Nonogram smileNonogram = new BooleanArrayNonogramReader(smileArr, true).readNonogram();
        
        Assertions.assertTrue(new NonogramOperations(nonogram).equals(smileNonogram));
    }
    
}
