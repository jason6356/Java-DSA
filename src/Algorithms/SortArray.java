package Algorithms;

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
            getIndexOfSmallest(a, first, last);

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

}
