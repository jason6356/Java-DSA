package Algorithms;

import java.util.Arrays;

public class CountingSort {

    private final int[] arr;

    public CountingSort(int[] arr) {
        this.arr = arr;
    }

    public void sortV2() {
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);

        int len = max - min + 1;
        int[] countArray = new int[len];

        for(int val : arr)
            countArray[val - min]++;

        //Cumulative Adding the values behind the elements
        for(int i = 1; i < len; i++)
            countArray[i] += countArray[i - 1];

        int[] output = new int[arr.length];

        for(int i = arr.length - 1; i >= 0 ; i--){
            int current = arr[i];
            int positionInArray = countArray[current - min] - 1;
            output[positionInArray] = current;
            countArray[current - min]--;
        }

        System.arraycopy(output,0, arr, 0 , arr.length);
    }

    public void sort(){
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);

       int len = max - min + 1;
        int[] countArray = new int[len];
        for (int value : arr) {
            countArray[value - min]++;
        }

        int arrayIndex = 0;
        for (int i = 0; i < len; i++) {
             while(countArray[i] > 0) {
                 arr[arrayIndex] = i + min;
                 countArray[i]--;
                 arrayIndex++;
             }
        }

    }

    public static void main(String[] args) {
       int[] arr = {5,2,8,7,-2,3,3,6,2,6,-1,1,2,7,-2,5,2,4,9};
        System.out.println(Arrays.toString(arr));
        new CountingSort(arr).sortV2();
        System.out.println(Arrays.toString(arr));
    }
}
