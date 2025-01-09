/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.infrastructure.reader;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.domain.ArrayNonogram;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
@RequiredArgsConstructor
public class BooleanArrayNonogramReader implements NonogramReader {
    
    private final Boolean[][] booleanArray;
    private final boolean fill;

    @Override
    public Nonogram readNonogram() {
        List<Integer[]> rows = new LinkedList();
        List<Integer[]> cols = new LinkedList();
        
        for (int i = 0; i < booleanArray.length; i++) {
            Integer[] row = NonogramOperations.groups(booleanArrayToStatus(booleanArray[i]));
            rows.add(row);
        }
        
        for (int i = 0; i < booleanArray[0].length; i++) {
            Boolean[] colArr = new Boolean[booleanArray.length];
            for (int j = 0; j < booleanArray.length; j++) colArr[j] = booleanArray[j][i];
            
            Integer[] col = NonogramOperations.groups(booleanArrayToStatus(colArr));
            cols.add(col);
        }
        
        Nonogram n = new ArrayNonogram(
                rows.toArray(size -> new Integer[size][]),
                cols.toArray(size -> new Integer[size][])
        );
        
        if (fill) {
            for (int i = 0; i < booleanArray.length; i++) for (int j = 0; j < booleanArray[i].length; j++) {
                n.val(j, i, booleanArray[i][j] ? CellStatus.ENABLED : CellStatus.DISABLED);
            }
        }
        
        return n;
    }
    
    private CellStatus[] booleanArrayToStatus(Boolean[] array) {
        CellStatus[] exit = new CellStatus[array.length];
        for (int i = 0; i < array.length; i++) exit[i] = array[i] ? CellStatus.ENABLED : CellStatus.DISABLED;
        return exit;
    }
    
}
