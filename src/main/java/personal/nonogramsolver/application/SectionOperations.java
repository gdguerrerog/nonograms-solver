/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Section;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class SectionOperations {
    @Getter
    private final Section section;
    
    public SectionOperations reverse() {
        CellStatus[] status = new CellStatus[section.size()];
        for (int i = 0; i < status.length; i++) status[i] = section.status(status.length - i - 1);

        
        Integer[] groups = section.grops();
        Integer[] reversedGroups = new Integer[groups.length];
        for (int i = 0; i < reversedGroups.length; i++) reversedGroups[i] = groups[groups.length - i - 1];
        
        return new SectionOperations(new ArraySection(reversedGroups, status));
    }
}
