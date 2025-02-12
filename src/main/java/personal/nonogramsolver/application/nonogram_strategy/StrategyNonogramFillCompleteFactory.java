/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application.nonogram_strategy;

import java.util.LinkedList;
import java.util.List;
import personal.nonogramsolver.application.NonogramOperations;
import personal.nonogramsolver.application.SectionOperations;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;
import personal.nonogramsolver.domain.Section;
import personal.nonogramsolver.domain.SectionAFD;

/**
 *
 * @author German at CLEZ
 */
public class StrategyNonogramFillCompleteFactory implements StrategyNonogramFactory {

    @Override
    public StrategyNonogram build() {
        return new StrategyNonogramFillComplete();
    }
    
    private class StrategyNonogramFillComplete implements StrategyNonogram {

        @Override
        public List<NonogramInformation> getInformation(Nonogram nonogram) {
            List<NonogramInformation> information = new LinkedList();
            
            NonogramOperations operations = new NonogramOperations(nonogram);
            for (int i = 0; i < nonogram.rows(); i++) {
                final int iFinal = i;
                getFillElementsIfComplete(operations.colSection(iFinal))
                        .stream()
                        .map(idx -> new NonogramInformation(CellStatus.DISABLED, iFinal, idx))
                        .forEach(information::add);
            }
            
            for (int i = 0; i < nonogram.cols(); i++) {
                final int iFinal = i;
                getFillElementsIfComplete(operations.rowSection(iFinal))
                        .stream()
                        .map(idx -> new NonogramInformation(CellStatus.DISABLED, idx, iFinal))
                        .forEach(information::add);
            }
            
            return information;
        }
        
        private List<Integer> getFillElementsIfComplete(Section serction) {
            SectionOperations operations = new SectionOperations(serction);
            
            final int NO_COUNTING = 0, COUNTING = 1;
            
            SectionAFD.IterateResult result = new SectionAFD<GetElementCompleteAccumulator>(serction, NO_COUNTING, (params)-> {
                switch (params.status()) {
                    case NO_COUNTING -> {
                        switch (params.cell()) {
                            case ENABLED: return new SectionAFD.EvaluateResult(COUNTING, params.acc().inc());
                            default: return new SectionAFD.EvaluateResult(NO_COUNTING, params.acc());
                        }
                    }
                    case COUNTING -> {
                        switch (params.cell()) {
                            case ENABLED: return new SectionAFD.EvaluateResult(COUNTING, params.acc().inc());
                            default:
                                if (params.acc().currentGroupSize() != params.group()) return new SectionAFD.EvaluateResult(NO_COUNTING, false, new GetElementCompleteAccumulator(false, 0), true);
                                return new SectionAFD.EvaluateResult(NO_COUNTING, true, new GetElementCompleteAccumulator(true, 0), false);    
                        }
                    }
                }
                
                return null;
            }, new GetElementCompleteAccumulator(true, 0))
            .setEvaluateLast(true)
            .iterate();
            
            
            // TODO
            return List.of();
            
        }
        
    }
    
    private record GetElementCompleteAccumulator(
            boolean ok,
            int currentGroupSize
    ){
        public GetElementCompleteAccumulator inc() {
            return new GetElementCompleteAccumulator(ok, currentGroupSize + 1);
        }
    }
    
}
