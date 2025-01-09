/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import java.util.Arrays;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class ArrayNonogram implements Nonogram {
    
    private final CellStatus[][] state; 
    private final Integer[][] rowInfo;
    private final Integer[][] colInfo;
    
    public ArrayNonogram(Integer[][] rowInfo, Integer[][] colInfo){
        this.state = new CellStatus[rowInfo.length][colInfo.length];
        Arrays.stream(state).forEach(row -> Arrays.fill(row, CellStatus.UNKNOWN));
        
        this.rowInfo = rowInfo;
        this.colInfo = colInfo;
    }
    
    @Override
    public int cols() {
        return state[0].length;
    }

    @Override
    public int rows() {
        return state.length;
    }

    @Override
    public Integer[] getRowInfo(int index) {
        return rowInfo[index];
    }

    @Override
    public Integer[] getColInfo(int index) {
        return colInfo[index];
    }

    @Override
    public CellStatus getCellStatus(int x, int y) {
        return state[y][x];
    }

    @Override
    public void setCellStatus(int x, int y, CellStatus status) {
        state[y][x] = status;
    }
    
}
