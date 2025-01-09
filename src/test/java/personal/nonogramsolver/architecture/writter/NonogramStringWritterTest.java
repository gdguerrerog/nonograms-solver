/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.architecture.writter;

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
    public void testString() {
        NonogramStringWritter w = new NonogramStringWritter();
        String representation = w.writeNonogram(provider.random(15, 15, true));
        
        System.out.println(representation);



    }
    
}
