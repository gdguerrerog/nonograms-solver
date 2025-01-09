/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
@RequiredArgsConstructor
public class NonogramOperations {
    
    @Getter 
    private final Nonogram nonogram;
    
    public Integer[] groupsRow(int row) {
        return groups(row(row));
    }
    
    public Integer[] groupsCol(int col) {
        return groups(col(col));
    }
    
    public static Integer[] groups(CellStatus[] cells) {
        List<Integer> groups = new LinkedList();
        
        int count = 0;
        for (CellStatus s: cells) {
            if (s == CellStatus.ENABLED) count++;
            else if (count != 0) {
                groups.add(count);
                count = 0;
            }
        }
        
        if (count != 0) groups.add(count);
        
        return groups.toArray(size -> new Integer[size]);
    }
    
    public CellStatus[] row(int index) {
        CellStatus[] row = new CellStatus[nonogram.cols()];
        for (int i = 0; i < nonogram.cols(); i++) row[i] = nonogram.getCellStatus(i, index);
        return row;
    }
    
    public CellStatus[] col(int index) {
        CellStatus[] col = new CellStatus[nonogram.rows()];
        for (int i = 0; i < nonogram.rows(); i++) col[i] = nonogram.getCellStatus(index, i);
        return col;
    }
    
    
}
