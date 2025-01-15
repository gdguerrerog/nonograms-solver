/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.DISABLED;
import static personal.nonogramsolver.domain.CellStatus.ENABLED;
import static personal.nonogramsolver.domain.CellStatus.UNKNOWN;
import personal.nonogramsolver.domain.IllegalNonogramStatusException;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.SectionAFD;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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
        SectionAFD<SectionSpaceAcummulator> afd = new SectionAFD<SectionSpaceAcummulator> (section, FILLED_GROUP, (params) -> {
            switch (params.status()) {
                case FILLED_GROUP: {
                    switch (params.cell()) {
                        case DISABLED: return new SectionAFD.EvaluateResult(FILLED_GROUP, false, new SectionSpaceAcummulator(params.acc().spaces, null, 0, false));
                        case ENABLED, UNKNOWN:  {
                            SectionSpaceAcummulator newAcc = new SectionSpaceAcummulator(
                                    params.acc().spaces, 
                                    new LinkedList<>(), 
                                    params.index(), 
                                    params.cell() == UNKNOWN
                            );
                            newAcc.readedStatus.add(params.cell());
                            return new SectionAFD.EvaluateResult(READING_GROUP, false, newAcc);
                        }
                     }
                }
                case READING_GROUP: {
                    switch (params.cell()) {
                        case DISABLED: {
                            params.acc().spaces.add(new SectionSpace(params.acc().groupIndex, params.acc().readedStatus.toArray(size -> new CellStatus[size]), !params.acc().hasSpaces));
                            return new SectionAFD.EvaluateResult(FILLED_GROUP, false, new SectionSpaceAcummulator(
                                    params.acc().spaces, 
                                    null, 
                                    0, 
                                    false
                            ));
                        }
                        case ENABLED, UNKNOWN: {
                            params.acc().readedStatus.add(params.cell());
                            boolean hasSpaces = params.acc().hasSpaces || params.cell() == UNKNOWN;
                            return new SectionAFD.EvaluateResult(READING_GROUP, false, new SectionSpaceAcummulator(
                                    params.acc().spaces, 
                                    params.acc().readedStatus, 
                                    params.acc().groupIndex, 
                                    hasSpaces
                            ));
                        }
                    }
                }
                default: return null;
            }
            
        }, new SectionSpaceAcummulator(new LinkedList(), null, 0, false));
        
        SectionAFD.IterateResult<SectionSpaceAcummulator> result = afd.iterate();
        
        if (result.status() == READING_GROUP) result.acc().spaces.add(new SectionSpace(result.acc().groupIndex, result.acc().readedStatus.toArray(size -> new CellStatus[size]), !result.acc().hasSpaces)); 
        
        return result.acc().spaces;
    }
    
    protected static Tuple2<List<SectionSpace>, Integer[]> removeBorders(List<SectionSpace> spaces, Integer[] groups) {
        ListIterator<SectionSpace> spacesIt = spaces.listIterator();
        int groupBeginNoFilledIndex = 0;
        while(spacesIt.hasNext()) {
            SectionSpace se = spacesIt.next();
            if (!se.completed) {
                spacesIt.previous();
                break;
            }
            if (se.values.length != groups[spacesIt.previousIndex()]) throw new IllegalNonogramStatusException();
            groupBeginNoFilledIndex++;
        }
        
        List<SectionSpace> spacesWithoutBorders =  new LinkedList();
        int groupEndNoFilledIndex = groupBeginNoFilledIndex;
        
        List<SectionSpace> currentFilledSpaces = new LinkedList();
        while(spacesIt.hasNext()) {
            SectionSpace se = spacesIt.next();
            if (se.completed) currentFilledSpaces.add(se);
            else {
                spacesWithoutBorders.addAll(currentFilledSpaces);
                currentFilledSpaces.clear();
                spacesWithoutBorders.add(se);
                groupEndNoFilledIndex = spacesIt.nextIndex();
            }
        }
        
        Integer[] groupsNoFilled = new Integer[groupEndNoFilledIndex - groupBeginNoFilledIndex];
        for (int i = 0; i < groupsNoFilled.length; i++) groupsNoFilled[i] = groups[i + groupBeginNoFilledIndex];
        return Tuples.of(spacesWithoutBorders, groupsNoFilled);
    }

    protected static record SectionSpace(
        int startIndex,
        CellStatus[] values,
        boolean completed
    ){}
    
    public static record SplitSectionResult (
        int index,
        Section section
    ){}
    
    private static record SpaceCount(
        int count,
        boolean compleated
    ){}
    
    private static record SectionSpaceAcummulator (
        List<SectionSpace> spaces,
        List<CellStatus> readedStatus,
        int groupIndex,
        boolean hasSpaces
    ){}
}
