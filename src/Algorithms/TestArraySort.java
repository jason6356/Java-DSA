package Algorithms;

import java.util.Arrays;

public class TestArraySort {

    public static void main(String[] args) {
        Integer[] arr = {7, 5, 9, 3, 6, 0, 2, 4};

        SortArray.quickSort(arr, 0, arr.length - 1);

        System.out.println("After Sorting");
        Arrays.stream(arr).forEach(System.out::println);

    }
}
