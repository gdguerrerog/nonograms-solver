/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.testutils;

import java.util.Random;
import java.util.stream.IntStream;
import personal.nonogramsolver.infrastructure.reader.BooleanArrayNonogramReader;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class NonogramProvider {
    
    private static final Boolean t = true;
    private static final Boolean f = false;
    
    public static final Boolean[][] n1x1 = {{t}};
    public static final Boolean[][] n2x2 = {
        {t, f}, 
        {f, t}
    };
    public static final Boolean[][] n3x3 = {
        {t, t, t}, 
        {t, f, t}, 
        {t, f, t}
    };
    
    // Singleton
    private NonogramProvider() {}
    private static NonogramProvider instance = null;
    public static NonogramProvider get() {
        if (instance == null) instance = new NonogramProvider();
        return instance;
    }
    
    
    
    public Nonogram get1X1(boolean fill) {
        return new BooleanArrayNonogramReader(n1x1, fill).readNonogram();
    }
    
    public Nonogram get2X2(boolean fill) {
        return new BooleanArrayNonogramReader(n2x2, fill).readNonogram();
    }
    
    public Nonogram get3X3(boolean fill) {
        return new BooleanArrayNonogramReader(n3x3, fill).readNonogram();
    }
    
    public Nonogram random(int xSize, int ySize, boolean fill){
        Random r =  new Random();
        Boolean[][] randomBoolean = IntStream.range(0, ySize)
                .mapToObj(i -> 
                        IntStream.range(0, xSize)
                                .mapToObj(j -> r.nextBoolean())
                                .toArray(size -> new Boolean[size])
                )
                .toArray(size -> new Boolean[size][]);
        
        return new BooleanArrayNonogramReader(randomBoolean, fill).readNonogram();

    }
    
}
