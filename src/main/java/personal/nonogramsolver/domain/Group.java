/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.domain;

import java.util.Iterator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author German at CLEZ
 */
public interface Group extends Iterable<Integer> {
    int size();
    Integer val(int index);
    
    default Iterator<Integer> iterator() {
        return new GroupInterator(this);
    }
    
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    class GroupInterator implements Iterator<Integer> {
        private final Group group;
        private int index = 0;
        @Override public boolean hasNext() { return index < group.size(); }
        @Override public Integer next() { return group.val(index++); }
    }
}
