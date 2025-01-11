/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.List;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German at CLEZ
 */
public interface StrategyNonogram {
    
    List<NonogramInformation> getInformation(Nonogram nonogram);
    
    record NonogramInformation(
            CellStatus status,
            int x,
            int y
    ) {}
    
}
