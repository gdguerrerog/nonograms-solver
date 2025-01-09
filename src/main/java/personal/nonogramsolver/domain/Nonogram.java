/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.domain;

/**
 *
 * @author German Guerrero at CLEZ
 */
public interface Nonogram {
    
    int cols();
    int rows();
    
    default Integer[] getSize() { return new Integer[]{cols(), rows()}; }
    
    Integer[] getRowInfo(int index);
    Integer[] getColInfo(int index);
    
    CellStatus getCellStatus(int x, int y);
    void setCellStatus(int x, int y, CellStatus status);
    
}
