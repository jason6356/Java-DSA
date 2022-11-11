package Algorithms;

public class SearchArray {

    public static <T> boolean inArray(T[] anArray, T anEntry){
        boolean found = false;
        int index = 0;
        while(!found && (index < anArray.length)){
            if(anEntry.equals(anArray[index]))
                found = true;
            index++;
        }
        return  found;
    }

    public static <T> boolean recursiveInArray(T[] anArray, T anEntry){
        return search(anArray,0, anArray.length - 1, anEntry);
    }

    public static <T> boolean search(T[] anArray,int first, int last, T desiredItem){
        boolean found;
        if( first > last)
            found = false;
        else if(desiredItem.equals(anArray[first]))
            found = true;
        else
            found = search(anArray,first + 1, last, desiredItem);

        return found;
    }

    private static <T extends Comparable<? super T>> boolean binarySearch(T[] anArray,int first, int last, T desiredItem){
        boolean found = false;
        int mid = first + (last - first) / 2;
        if(first > last)
            found = false;
        else if(desiredItem.equals(anArray[mid]))
            found = true;
        else if(desiredItem.compareTo(anArray[mid]) < 0)
            found = binarySearch( anArray,first, mid - 1, desiredItem);
        else
            found = binarySearch( anArray,mid + 1, last, desiredItem);

        return found;
    }

    private static <T extends Comparable<? super T>> boolean binSearch(T[] anArray,T desiredItem){
        return binarySearch(anArray, 0, anArray.length - 1, desiredItem);
    }

    public static void main(String[] args) {

        Integer[] ls = {1,2,3,9,10,20,23};

        System.out.println(recursiveInArray(ls,1));

    }
}
