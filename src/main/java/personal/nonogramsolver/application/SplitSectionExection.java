/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.DISABLED;
import static personal.nonogramsolver.domain.CellStatus.ENABLED;
import static personal.nonogramsolver.domain.CellStatus.UNKNOWN;
import personal.nonogramsolver.domain.Section;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class SplitSectionExection {

    // Get spaces state
    private static final int FILLED_GROUP = 0;
    private static final int READING_GROUP = 1;

    
    private final Section section;
    
    public List<SplitSectionResult> split(){
        
        List<SectionSpace> spaces = getSpaces();
        
        
        return List.of();
    }
    
    protected List<SectionSpace> getSpaces() {
        
        List<SectionSpace> spaces = new LinkedList();
        
        List<CellStatus> readedStatus = null;
        int currentState = FILLED_GROUP, index = 0, groupIndex = 0;
        boolean hasSpaces = false;
        for (CellStatus status: section) {
            switch (currentState) {
                case FILLED_GROUP -> {
                    switch (status) {
                        case DISABLED -> {}
                        case ENABLED, UNKNOWN -> {
                            groupIndex = index;
                            currentState = READING_GROUP;
                            readedStatus = new LinkedList();
                            readedStatus.add(status);
                            hasSpaces = status == UNKNOWN;
                        }
                     }
                }
                case READING_GROUP -> {
                    switch (status) {
                        case DISABLED -> {
                            if (hasSpaces) spaces.add(new SectionSpace(groupIndex, readedStatus.toArray(size -> new CellStatus[size])));
                            currentState = FILLED_GROUP;
                        }
                        case ENABLED -> readedStatus.add(status);
                        case UNKNOWN -> {
                            readedStatus.add(status);
                            hasSpaces = true;
                        }
                     }
                }
            }
            index++;
        }
        
        if (currentState == READING_GROUP && hasSpaces) spaces.add(new SectionSpace(groupIndex, readedStatus.toArray(size -> new CellStatus[size]))); 
        
        return spaces;
    }
    
    protected static record SectionSpace(
        int startIndex,
        CellStatus[] values
    ){}
    
    public static record SplitSectionResult (
        int index,
        Section section
    ){}
    
}
