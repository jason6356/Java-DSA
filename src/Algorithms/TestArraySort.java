package Algorithms;

public class TestArraySort {

    public static void main(String[] args) {
        String test = "test";

        int hashCodeFromJava = test.hashCode();
        int hashCodeFromOwn = getHashCode(test);

        System.out.println("Hash Code from Java : " + hashCodeFromJava);
        System.out.println("Hash Code from Own : " + hashCodeFromOwn);

    }

    public static int getHashCode(String s){
       int len = s.length();
       int hash = 0;
       int g = 31;
       for(int i = 0; i < len; i++)
          hash = g * hash + s.charAt(i);

       return hash;
    }
}
