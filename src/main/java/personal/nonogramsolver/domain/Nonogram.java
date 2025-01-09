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
    
    default Integer[] size() { return new Integer[]{cols(), rows()}; }
    
    Integer[] row(int index);
    Integer[] col(int index);
    
    CellStatus val(int x, int y);
    void val(int x, int y, CellStatus status);
    
}
