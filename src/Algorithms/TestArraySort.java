package Algorithms;

import java.util.Arrays;

public class TestArraySort {

    public static void main(String[] args) {
        Integer[] arr = {7, 5, 9, 3, 6, 0, 2, 4};

        arr = Arrays.stream(arr)
                .map((e) -> e + 10)
                .toArray(Integer[]::new);

        Arrays.stream(arr).forEach(System.out::println);

        SortArray.mergeSort(arr, 0, arr.length - 1);

        System.out.println("After Sorting");

        Arrays.stream(arr).forEach(System.out::println);
    }
}
