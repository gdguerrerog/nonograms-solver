/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

/**
 *
 * @author German at CLEZ
 */
public class ArrayGroupSpace extends ArraySpace implements GroupSpace {
    
    private final Group group; 
    
    public ArrayGroupSpace(CellStatus[] array, int sectionIdx, Group group) {
        super(array, sectionIdx);
        this.group = group;
    }

    @Override
    public Group group() {
        return group;
    }
    
}
