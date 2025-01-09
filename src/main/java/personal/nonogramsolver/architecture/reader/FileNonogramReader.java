/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.infrastructure.reader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArrayNonogram;
import personal.nonogramsolver.domain.CellStatus;
import personal.nonogramsolver.domain.Nonogram;

/**
 *
 * @author German Guerrero at CLEZ
 */
@RequiredArgsConstructor
public class FileNonogramReader implements NonogramReader {

    private final String location;
    
    @Override
    public Nonogram readNonogram() {
        
        ObjectMapper mapper = new ObjectMapper();
        NonogramFileFormat format;
        
        try {
            format = mapper.readValue(getClass().getClassLoader().getResourceAsStream("nonograms/" + location), NonogramFileFormat.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } 
        
        Nonogram nonogram = new ArrayNonogram(format.rows.toArray(size -> new Integer[size][]), format.cols.toArray(size -> new Integer[size][]));
        
        int lineCount = 0;
        for (String line: format.info) {
            for (int j = 0; j < line.length(); j++) switch(line.charAt(j)) {
                case '#' -> nonogram.val(j, lineCount, CellStatus.ENABLED);
                case '-' -> nonogram.val(j, lineCount, CellStatus.DISABLED);
            }
            
            lineCount++;
        }
        
        return nonogram;
    }
    
    private record NonogramFileFormat(
        @JsonProperty List<Integer[]> rows,
        @JsonProperty List<Integer[]> cols,
        @JsonProperty List<String> info
    ){}
    
}
