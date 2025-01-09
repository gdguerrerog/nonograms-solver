/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.infrastructure.writter;

import java.util.stream.IntStream;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
public class NonogramStringWritter implements NonogramWritter<String> {

    @Override
    public String writeNonogram(Nonogram n) {
        int maxRows = IntStream
                .range(0, n.rows())
                .map(i -> n.row(i).length)
                .reduce(-1, (v1, v2) -> Math.max(v1, v2));

        int maxCols = IntStream
                .range(0, n.cols())
                .map(i -> n.col(i).length)
                .reduce(-1, (v1, v2) -> Math.max(v1, v2));
        
        StringBuilder str = new StringBuilder();
        
        // Draw Cols at the begining
        for (int i = 0; i < maxCols; i++) {
            for(int j = 0; j < maxRows + 1; j++) str.append(" ");
            
            for (int j = 0; j < n.cols(); j++) {
                Integer[] colInfo = n.col(j);

                if (colInfo.length >= maxCols - i) str.append(colInfo[i - maxCols + colInfo.length]);
                else str.append(" ");  
            }
            
            str.append("\n");
        }
        str.append("\n");
        
        for (int i = 0; i < n.rows(); i++) {
            // Draw Rows at the begining
            Integer[] rowInfo = n.row(i);
            for (int j = 0; j < maxRows; j++) {
                if (rowInfo.length >= maxRows - j) str.append(rowInfo[j - maxRows + rowInfo.length]);
                else str.append(" ");
            }
            
            str.append(" ");
            
            for (int j = 0; j < n.cols(); j++) {
                char ch = switch (n.val(j, i)) {
                    case ENABLED -> '#';
                    case DISABLED -> '-';
                    case UNKNOWN -> ' ';
                };
                
                str.append(ch);
            }
            
            str.append("\n");
        }
        
        return str.toString();
    }
    
}
