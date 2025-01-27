/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package personal.nonogramsolver.application.section_strategy;
import java.util.List;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.GroupSpace;
import personal.nonogramsolver.domain.Section;

/**
 *
 * @author German at CLEZ
 */
public interface StrategySection {
    
    InformationResult getInformation(GroupSpace space);
    
    record InformationResult(
        boolean completable,
        List<SectionInformation> information
    ){}
    
    record SectionInformation (
        CellStatus status,
        int index
    ){}
}
