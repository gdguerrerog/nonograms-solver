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
@RequiredArgsConstructor
public class ArrayGroup implements Group {
    private final Integer[] array;

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Integer val(int index) {
        return array[index];
    }
    
    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
