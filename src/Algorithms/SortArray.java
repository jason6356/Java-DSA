package Algorithms;

import java.util.Arrays;
import java.util.Random;

public class SortArray {

    public static <T extends Comparable<? super T>> void selectionSort(T[] a, int n) {

        for (int i = 0; i < n - 1; i++) {
            int indexOfNextSmallest = getIndexOfSmallest(a, i, n - 1);
            swap(a, i, indexOfNextSmallest);
        }

    }


    public static <T extends Comparable<? super T>> void recursiveSelectionSort(T[] a, int n) {
        selectionSort(a, 0, n - 1);
    }


    public static <T extends Comparable<? super T>> void selectionSort(T[] a, int first, int last) {
        if (first < last) {
            int smallestIndex = getIndexOfSmallest(a, first, last);
            swap(a,first,smallestIndex);

            selectionSort(a, first + 1, last);
        }
    }

    private static <T extends Comparable<? super T>> int getIndexOfSmallest(T[] a, int first, int last) {
        T min = a[first];
        int indexOfMin = first;
        for (int i = first + 1; i <= last; i++) {
            if (a[i].compareTo(min) < 0) {
                min = a[i];
                indexOfMin = i;
            }

        }
        return indexOfMin;
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int first, int last) {
        for (int unsorted = first + 1; unsorted < last; unsorted++) {
            T nextToInsert = a[unsorted];
            insertInOrder(nextToInsert, a, first, unsorted - 1);
        }
    }


    public static <T extends Comparable<? super T>> void insertInOrder(T anEntry, T[] a, int begin, int end) {
        int index = end;

        while ((index >= begin) && (anEntry.compareTo(a[index]) < 0)) {
            a[index + 1] = a[index];
            index--;
        }
        a[index + 1] = anEntry;
    }


    public static <T extends Comparable<? super T>> void recursiveInsertionSort(T[] a, int first, int last) {
        if (first < last) {
            recursiveInsertionSort(a, first, last - 1);
            recursiveInsertInOrder(a[last], a, first, last - 1);
        }
    }

    public static <T extends Comparable<? super T>> void recursiveInsertInOrder(T anEntry, T[] a, int begin, int end) {
        if (anEntry.compareTo(a[end]) >= 0)
            a[end + 1] = anEntry;
        else if (begin < end) {
            a[end + 1] = a[end];
            recursiveInsertInOrder(anEntry, a, begin, end - 1);
        } else {
            a[end + 1] = a[end];
            a[end] = anEntry;
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] a, int first, int last) {
        if (first < last) {
            int mid = (last + first) / 2;
            System.out.println(mid);
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
    }


    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void merge(T[] a, int first, int mid, int last) {

        int len1 = mid - first + 1;
        int len2 = last - mid;

        T[] leftArr = ((T[]) new Comparable[len1]);

        T[] rightArr = ((T[]) new Comparable[len2]);
        for (int i = 0; i < len1; i++) {
            leftArr[i] = a[first + i];
        }
        for (int i = 0; i < len2; i++) {
            rightArr[i] = a[mid + 1 + i];
        }
        int i, j, k;
        i = 0;
        j = 0;
        k = first;

        while (i < len1 && j < len2) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                a[k] = leftArr[i];
                i++;
            } else {
                a[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < len1) {
            a[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < len2) {
            a[k] = rightArr[j];
            j++;
            k++;
        }


        System.out.println("After merging ....");
        Arrays.stream(a).forEach(System.out::println);
    }


    public static <T extends Comparable<? super T>> void quickSort(T[] a, int first, int last) {
        if (first >= last) return;
        int pivotIndex = new Random().nextInt(last - first) + first;
        T pivot = a[pivotIndex];
        swap(a, pivotIndex, last);
        /*while left pointer and right pointer are not pointing at same pointer*/
        int leftPtr = first;
        int rightPtr = last;
        while (leftPtr < rightPtr) {//move leftPtr until encounter value > pivot
            while (a[leftPtr].compareTo(pivot) <= 0 && leftPtr < rightPtr)
                leftPtr++;

            //move rightPtr until encounter value < pivot
            while (a[rightPtr].compareTo(pivot) > 0 && leftPtr < rightPtr)
                rightPtr--;

            swap(a, leftPtr, rightPtr);
        }
        swap(a, last, leftPtr);
        quickSort(a, first, leftPtr - 1);
        quickSort(a, leftPtr + 1, last);
    }



}
