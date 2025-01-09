/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.architecture.writter;

import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
public interface NonogramWritter<T> {
    T writeNonogram(Nonogram nonogram);
}
