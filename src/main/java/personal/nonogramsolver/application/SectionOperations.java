/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArraySection;
import personal.nonogramsolver.domain.ArraySpace;
import personal.nonogramsolver.domain.CellStatus;
import static personal.nonogramsolver.domain.CellStatus.DISABLED;
import static personal.nonogramsolver.domain.CellStatus.ENABLED;
import static personal.nonogramsolver.domain.CellStatus.UNKNOWN;
import personal.nonogramsolver.domain.Group;
import personal.nonogramsolver.domain.IllegalNonogramStatusException;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.SectionAFD;
import personal.nonogramsolver.domain.Space;

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
        return new SectionOperations(new ArraySection(new GroupOperations(section.group()).reverse().getGroup(), status));
    }
    
    public List<SpaceOperations> spaces() {
        final int FILLED_GROUP = 0;
        final int READING_GROUP = 1;
        
        SectionAFD<SectionSpaceAccummulator> afd = new SectionAFD<> (section, FILLED_GROUP, (params) -> {
            switch (params.status()) {
                case FILLED_GROUP: {
                    switch (params.cell()) {
                        case DISABLED: return new SectionAFD.EvaluateResult(FILLED_GROUP, new SectionSpaceAccummulator(params.acc().spaces, null, 0));
                        case ENABLED, UNKNOWN:  {
                            SectionSpaceAccummulator newAcc = new SectionSpaceAccummulator(params.acc().spaces, new LinkedList(), params.index());
                            newAcc.readedStatus.add(params.cell());
                            return new SectionAFD.EvaluateResult(READING_GROUP, newAcc);
                        }
                    }
                }
                case READING_GROUP: {
                    switch (params.cell()) {
                        case DISABLED: {
                            params.acc().spaces.add(new ArraySpace(params.acc().readedStatus.toArray(size -> new CellStatus[size]), params.acc().groupIndex));
                            return new SectionAFD.EvaluateResult(FILLED_GROUP, new SectionSpaceAccummulator(params.acc().spaces, null, 0));
                        }
                        case ENABLED, UNKNOWN: {
                            params.acc().readedStatus.add(params.cell());
                            return new SectionAFD.EvaluateResult(READING_GROUP, new SectionSpaceAccummulator(params.acc().spaces, params.acc().readedStatus, params.acc().groupIndex));
                        }
                    }
                }
                default: return null;
            }
            
        }, new SectionSpaceAccummulator(new LinkedList(), null, 0));
        
        SectionAFD.IterateResult<SectionSpaceAccummulator> result = afd.iterate();
        
        if (result.status() == READING_GROUP) result.acc().spaces.add(new ArraySpace(result.acc().readedStatus.toArray(size -> new CellStatus[size]), result.acc().groupIndex)); 
        
        return result.acc().spaces.stream().map(space -> new SpaceOperations(space)).toList();
    }
    
    public RemoveBordersResult removeBorders() {
        RemoveBordersResult leftBorder = removeLeftBorder();
        SectionOperations rightBorder = leftBorder.section.reverse().removeLeftBorder().section.reverse();
        return new RemoveBordersResult(leftBorder.sectionShift, leftBorder.groupShift, rightBorder);
    }
    
    private RemoveBordersResult removeLeftBorder() {
        if (section.size() == 0) return new RemoveBordersResult(0, 0, this);
        
        final int READING_BORDER = 0, SUM_SPACE = 1;
        
        SectionAFD.IterateResult<SectionBorderAccumulator> acc = new SectionAFD<SectionBorderAccumulator>(section, READING_BORDER, (param) -> {
            switch (param.status()) {
                case READING_BORDER: switch (param.cell()) {
                    case DISABLED: return new SectionAFD.EvaluateResult<>(READING_BORDER, new SectionBorderAccumulator(param.index(), 0));
                    case ENABLED: return new SectionAFD.EvaluateResult<>(SUM_SPACE, new SectionBorderAccumulator(param.acc().lastDisabledIndex(), 1));
                    case UNKNOWN: return new SectionAFD.EvaluateResult<>(READING_BORDER, false, param.acc(), true);
                }
                case SUM_SPACE: switch (param.cell()) {
                    case DISABLED: 
                        if (param.group() != param.acc().enabledSpaceSum()) throw new IllegalNonogramStatusException();
                        return new SectionAFD.EvaluateResult<>(READING_BORDER, true, new SectionBorderAccumulator(param.index(), 0), false);
                    case ENABLED: return new SectionAFD.EvaluateResult<>(SUM_SPACE, new SectionBorderAccumulator(param.acc().lastDisabledIndex, param.acc().enabledSpaceSum + 1));
                    case UNKNOWN: return new SectionAFD.EvaluateResult<>(READING_BORDER, false, param.acc(), true);
                }
            }
            
            return null;
        }, new SectionBorderAccumulator(-1, 0)).iterate();
        
        Group newGroup = new GroupOperations(section.group()).subGroup(acc.groupIndex()).getGroup();
        Section subSection = subSection(acc.acc().lastDisabledIndex + 1, section.size(), newGroup);
        
        return new RemoveBordersResult(acc.acc().lastDisabledIndex + 1, acc.groupIndex(), new SectionOperations(subSection));
    }
    
    public Section subSection(int startIndex, int endIndex, Group group) {
        CellStatus[] arr = new CellStatus[endIndex - startIndex];
        for (int i = 0; i < arr.length; i++) arr[i] = section.status(i + startIndex);
        return new ArraySection(group, arr);
    }
    
    public CellStatus[] values() {
        CellStatus[] arr = new CellStatus[section.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = section.status(i);
        return arr;
    }
    
    private static record SectionSpaceAccummulator (
        List<Space> spaces,
        List<CellStatus> readedStatus,
        int groupIndex
    ){}
    
    private static record SectionBorderAccumulator(
        int lastDisabledIndex,
        int enabledSpaceSum
    ) {}
    
    public static record RemoveBordersResult(
        int sectionShift,
        int groupShift,
        SectionOperations section
    ){}
    
    @Override
    public String toString() {
        return section.toString();
    }
}
