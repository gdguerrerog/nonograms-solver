/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.ArraySpace;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Group;
import personal.nonogramsolver.domain.Space;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class SpaceOperations {
    @Getter
    private final Space space;
    
    public CellStatus[] statuses() {
        CellStatus[] arr = new CellStatus[space.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = space.val(i);
        return arr;
    }
    
    public boolean admits(Group group) {
        if (space.compleated()) {
            if (group.size() != 1) return false;
            return group.val(0) == space.size();
        }

        return space.size() >= new GroupOperations(group).minSize();
    }
    
    public SpaceOperations shiftLocation(int shiftValue) {
        Space space = new ArraySpace(statuses(), this.space.sectionIdx() + shiftValue);
        return new SpaceOperations(space);
    }
    
    @Override
    public String toString() {
        return space.toString();
    }
}
