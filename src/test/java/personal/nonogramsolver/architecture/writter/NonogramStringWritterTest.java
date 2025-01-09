/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.architecture.writter;

import personal.nonogramsolver.infrastructure.writter.NonogramStringWritter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.testutils.NonogramProvider;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class NonogramStringWritterTest {

    private static NonogramProvider provider;
    
    @BeforeAll
    public static void initializeProvider() {
        provider = NonogramProvider.get();
    }
    
    @Test
    public void printTest() {
        
        final String rep1x1 =   "  1\n\n" +
                                "1 #\n";
        
        final String rep2x2 =   "  11\n\n" +
                                "1 #-\n" +
                                "1 -#\n";
        
        final String rep3x3 =   "   313\n\n" +
                                " 3 ###\n" +
                                "11 #-#\n" +
                                "11 #-#\n";
        
        NonogramStringWritter w = new NonogramStringWritter();

        String representation = w.writeNonogram(provider.get1X1(true));
        Assertions.assertEquals(rep1x1, representation);
        
        representation = w.writeNonogram(provider.get2X2(true));
        Assertions.assertEquals(rep2x2, representation);
        
        representation = w.writeNonogram(provider.get3X3(true));
        Assertions.assertEquals(rep3x3, representation);
    }
    
}
