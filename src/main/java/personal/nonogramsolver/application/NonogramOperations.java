/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArrayNonogram;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.Section;

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
        for (int i = 0; i < nonogram.cols(); i++) row[i] = nonogram.val(i, index);
        return row;
    }
    
    public CellStatus[] col(int index) {
        CellStatus[] col = new CellStatus[nonogram.rows()];
        for (int i = 0; i < nonogram.rows(); i++) col[i] = nonogram.val(index, i);
        return col;
    }
    
    
    public boolean equals(Nonogram other) {
        
        if (nonogram.rows() != other.rows()) return false;
        if (nonogram.cols() != other.cols()) return false;
        
        for (int i = 0; i < nonogram.cols(); i++) if (!Arrays.equals(nonogram.col(i), other.col(i))) return false;
        for (int i = 0; i < nonogram.rows(); i++) if (!Arrays.equals(nonogram.row(i), other.row(i))) return false;
         
        return true;
    }
    
    public boolean equalsFill(Nonogram other) {
        
        if (!equals(other)) return false;
        
        for (int i = 0; i < nonogram.rows(); i++) for(int j = 0; j < nonogram.cols(); j++) {
            if (nonogram.val(j, i).compareTo(other.val(j, i)) != 0) return false; 
        }
        
        return true;
    }
    
    public Nonogram clone() {
        Integer[][] rows = IntStream.range(0, nonogram.rows()).mapToObj(nonogram::row).toArray(size -> new Integer[size][]);
        Integer[][] cols = IntStream.range(0, nonogram.cols()).mapToObj(nonogram::col).toArray(size -> new Integer[size][]);
        
        Nonogram clone = new ArrayNonogram(rows, cols);
        
        for (int i = 0; i < nonogram.rows(); i++) for (int j = 0; j < nonogram.cols(); j++) {
            clone.val(j, i, nonogram.val(j, i));
        }
        
        return clone;
        
    }
    
    public Section rowSection(int index) {
        CellStatus[] statuses = new CellStatus[nonogram.cols()];
        for (int i = 0; i < statuses.length; i++) statuses[i] = nonogram.val(i, index);
        return new ArraySection(nonogram.row(index), statuses);
    }
    
    public Section colSection(int index) {
        CellStatus[] statuses = new CellStatus[nonogram.rows()];
        for (int i = 0; i < statuses.length; i++) statuses[i] = nonogram.val(index, i);
        return new ArraySection(nonogram.col(index), statuses);
    }
    
}
