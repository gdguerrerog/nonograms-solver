/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class ArraySection implements Section {

    private final Integer[] groups;
    private final CellStatus[] sectionArray;
    
    @Override
    public Integer[] grops() {
        return groups;
    }

    @Override
    public int size() {
        return sectionArray.length;
    }

    @Override
    public CellStatus status(int index) {
        return sectionArray[index];
    }
    
}
