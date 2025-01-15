/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personal.nonogramsolver.application;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import personal.nonogramsolver.domain.ArrayGroup;
import personal.nonogramsolver.domain.Group;

/**
 *
 * @author German at CLEZ
 */
@RequiredArgsConstructor
public class GroupOperations {
    @Getter
    private final Group group;
    
    public int minSize() {
        return sum() + group.size() - 1;
    }
    
    public int sum() {
        int sum = 0;
        for (int i = 0; i < group.size(); i++) sum += group.val(i);
        return sum;
    }
    
    public int size() {
        return group.size();
    }
    
    public GroupOperations add(Integer i) {
        Integer[] newArr = new Integer[group.size() + 1];
        System.arraycopy(values(), 0, newArr, 0, group.size());
        newArr[newArr.length - 1] = i;
        return new GroupOperations(new ArrayGroup(newArr));
    }
    
    public GroupOperations subGroup(int start) {
        return subGroup(start, group.size());
    }
    
    public GroupOperations subGroup(int start, int end) {
        Integer[] arr = new Integer[end - start];
        for (int i = 0; i < arr.length; i++) arr[i] = group.val(i + start);
        return new GroupOperations(new ArrayGroup(arr));
    }
    
    public Integer[] values() {
        Integer[] arr = new Integer[group.size()];
        for (int i = 0; i < arr.length; i++) arr[i] = group.val(i);
        return arr;
    }
    
    public GroupOperations reverse() {
        Integer[] reversed = new Integer[group.size()];
        for (int i = 0; i < reversed.length; i++) reversed[i] = group.val(group.size() - i - 1);
        return new GroupOperations(new ArrayGroup(reversed));
    }
    
    
}
