/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import personal.nonogramsolver.testutils.NonogramProvider;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class NonogramOperationsTest {
    
    private static NonogramProvider provider;
    
    @BeforeAll
    public static void initializeProvider() {
        provider = NonogramProvider.get();
    }
    
    @Test
    public void extractGroupsTest() {
        NonogramOperations op = new NonogramOperations(provider.get1X1(true));
        
        Assertions.assertArrayEquals(new Integer[]{1}, op.groupsCol(0));
        Assertions.assertArrayEquals(new Integer[]{1}, op.groupsRow(0));
        
        op = new NonogramOperations(provider.get3X3(true));
        
        Assertions.assertArrayEquals(new Integer[]{3}, op.groupsCol(0));
        Assertions.assertArrayEquals(new Integer[]{1}, op.groupsCol(1));
        Assertions.assertArrayEquals(new Integer[]{3}, op.groupsCol(2));
        
        Assertions.assertArrayEquals(new Integer[]{3}, op.groupsRow(0));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, op.groupsRow(1));
        Assertions.assertArrayEquals(new Integer[]{1, 1}, op.groupsRow(2));
    }
    
}
