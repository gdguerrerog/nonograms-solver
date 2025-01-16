/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class SectionAFD<TAcc> {
    
    private final Section section;
    private final int initialStatus;
    private final EvaluateFuction<TAcc> func;
    private final TAcc accumulator;
    
    public IterateResult<TAcc> iterate() {
        
        int status = initialStatus;
        int groupIndex = 0;
        Group group = section.group();
        
        TAcc acc = accumulator;
        
        for (int i = 0; i < section.size(); i++) {
            Integer groupVal = groupIndex >= group.size() ? null : group.val(groupIndex);
            EvaluateResult<TAcc> result = func.evaluate(new EvaluateParams<>(status, i, groupVal, section.status(i), acc));

            
            if (result.nextGroup) groupIndex++;
            status = result.nextStatus;
            acc = result.acc;

            if (result.terminate) break;
        }
        
        return new IterateResult(status, groupIndex, acc);
    }
    
    @FunctionalInterface
    public static interface EvaluateFuction<TAcc> {
        EvaluateResult<TAcc> evaluate(EvaluateParams<TAcc> params);
    }
    
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @Getter()
    public static class EvaluateParams<TAcc> {
        private final int status;
        private final int index;
        private final Integer group;
        private final CellStatus cell;
        private final TAcc acc;
    }
    
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @Getter
    public static class EvaluateResult<TAcc> {
        private final int nextStatus;
        private final boolean nextGroup;
        private final TAcc acc;
        private final boolean terminate;
        
        public EvaluateResult(int nextStatus, TAcc acc) {
            this.nextStatus = nextStatus;
            this.nextGroup = false;
            this.acc = acc;
            this.terminate = false;
        }
    }
    
    @RequiredArgsConstructor
    @Accessors(fluent = true)
    @Getter
    public static class IterateResult<TAcc> {
        private final int status;
        private final int groupIndex;
        private final TAcc acc;
    }
}
