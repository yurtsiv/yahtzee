package main;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayUtils {
    public static ArrayList<Integer> takeIndexes (int[] indexes, int[] array) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int index : indexes) {
            result.add(array[index - 1]);
        }
        return result;
    }


    public static boolean areAllElemsEqual (int[] array) {
        int firstElem = array[0];
        for (int elem : array) {
            if (elem != firstElem) {
                return false;
            }
        }
        return true;
    }
}
