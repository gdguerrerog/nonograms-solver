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

    private final Group group;
    private final CellStatus[] sectionArray;
    
    public ArraySection(Integer[] group, CellStatus[] sectionArray) {
        this.group = new ArrayGroup(group);
        this.sectionArray = sectionArray;
    }
    
    @Override
    public Group group() {
        return group;
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
