/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.GroupSpace;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.Space;

/**
 *
 * @author German at CLEZ
 */
public class GroupSpaceOperations extends SpaceOperations {
    
    private final GroupSpace groupSpace;
    
    public GroupSpaceOperations(GroupSpace groupSpace) {
        super(groupSpace);
        this.groupSpace = groupSpace;
    }
    
    public SectionOperations section() {
        Section section = new ArraySection(groupSpace.group(), statuses());
        return new SectionOperations(section);
    }
    
}
