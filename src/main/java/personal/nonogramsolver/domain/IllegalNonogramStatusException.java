/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.domain;

import lombok.NoArgsConstructor;

/**
 *
 * @author German at CLEZ
 */
@NoArgsConstructor
public class IllegalNonogramStatusException extends RuntimeException {
    
    public IllegalNonogramStatusException(String info) {
        super(info);
    }
    
}
