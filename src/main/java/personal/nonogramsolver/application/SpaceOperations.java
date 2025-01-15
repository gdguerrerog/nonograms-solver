/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.CellStatus;
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
}
