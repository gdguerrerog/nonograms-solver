/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

/**
 *
 * @author German at CLEZ
 */
public interface Space {
    int size();
    CellStatus val(int index);
    boolean compleated();
    int sectionIdx();
}
