/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class ReadOnlyNonogram implements Nonogram {

    private final Nonogram nonogram;
    
    @Override
    public int cols() {
        return nonogram.cols();
    }

    @Override
    public int rows() {
        return nonogram.rows();
    }

    @Override
    public Integer[] row(int index) { 
        return Arrays.copyOf(nonogram.row(index), rows());
    }

    @Override
    public Integer[] col(int index) {
        return Arrays.copyOf(nonogram.col(index), cols());
    }

    @Override
    public CellStatus val(int x, int y) {
        return nonogram.val(x, y);
    }

    @Override
    public void val(int x, int y, CellStatus status) {
        throw new RuntimeException("Cant edit nonogram");
    }
    
}
