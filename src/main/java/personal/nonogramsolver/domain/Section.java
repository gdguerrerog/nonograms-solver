/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import java.util.Iterator;

/**
 *
 * @author German at CLEZ
 */
public interface Section extends Iterable<CellStatus> {
    Group group();
    int size();
    CellStatus status(int index);
    
    default Iterator<CellStatus> iterator() {
        return new Iterator<CellStatus>() {
            int index = 0;
            @Override public boolean hasNext() { return index < size(); }
            @Override public CellStatus next() { return status(index++); }
        };
        
    }
}
