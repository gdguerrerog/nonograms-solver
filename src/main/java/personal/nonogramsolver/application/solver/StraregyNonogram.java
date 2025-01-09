/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.application.solver;

import java.util.List;
import personal.nonogramsolver.domain.CellStatus;

/**
 *
 * @author German at CLEZ
 */
public interface StraregyNonogram {
    
    List<NonogramInformation> getInformation();
    
    record NonogramInformation(
            CellStatus status,
            int x,
            int y
    ) {}
    
}
