package org.example;

public final class ParallelArraySort {

    private ParallelArraySort() {}

    public static <T extends Comparable<T>>
    int[] indirectSort(final T[] array) {
        int[] index = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            index[i] = i;
        }
        int arraySize = array.length;
        for (int i = 1; i < arraySize; i++) {
            int keyIndex = index[i];
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                index[j + 1] = index[j];
                j = j - 1;
            }

            array[j + 1] = key;
            index[j + 1] = keyIndex;
        }
        return index;
    }
}
