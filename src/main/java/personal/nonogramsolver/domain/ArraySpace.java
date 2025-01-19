/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author German at CLEZ
 */
public class ArraySpace implements Space {

    private final CellStatus[] array;
    private final int sectionIdx;
    private final boolean compleated;
    
    public ArraySpace(CellStatus[] array, int sectionIdx) {
        this.array = array;
        this.sectionIdx = sectionIdx;

        boolean compleated = true;
        for (int i = 0; i < array.length; i++) {
            switch (array[i]) {
                case DISABLED: throw new IllegalArgumentException("Not a space");
                case UNKNOWN: 
                    compleated = false;
                    break;
            }
        }
        this.compleated = compleated;
    }
    
    @Override
    public int size() {
        return array.length;
    }

    @Override
    public CellStatus val(int index) {
        return array[index];
    }

    @Override
    public boolean compleated() {
        return compleated;
    }

    @Override
    public int sectionIdx() {
        return sectionIdx;
    }
    
    @Override
    public String toString() {
        return String.format("[idx=%d, space=%s]", this.sectionIdx, Arrays.toString(array)) ;
    }
    
}
