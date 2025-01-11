/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;
import java.util.List;
import personal.nonogramsolver.domain.CellStatus;

/**
 *
 * @author German at CLEZ
 */
public interface StrategySection {
    
    List<SectionInformation> getInformation(CellStatus[] section);
    
    record SectionInformation (
        CellStatus status,
        int index
    ){}
}
