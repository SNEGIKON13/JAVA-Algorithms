package org.example;

import java.util.Arrays;

public class HybridSort {

    private static final int CUTOFF = 15; // Порог для переключения на трёхчастную быструю сортировку

    public static void main(String[] args) {
        String[] array = {
                "radix", "sort", "is", "a", "fast", "sorting", "algorithm",
                "for", "integers", "and", "strings", "of", "fixed", "lengths"
        };

        hybridSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void hybridSort(String[] array) {
        hybridSort(array, 0, array.length - 1, 0);
    }

    private static void hybridSort(String[] array, int low, int high, int d) {
        if (high <= low + CUTOFF) {
            threeWayQuickSort(array, low, high, d);
            return;
        }

        int[] count = new int[258];
        String[] aux = new String[high - low + 1];

        for (int i = low; i <= high; i++) {
            int c = charAt(array[i], d) + 2;
            count[c]++;
        }

        for (int r = 0; r < 257; r++) {
            count[r + 1] += count[r];
        }

        for (int i = low; i <= high; i++) {
            int c = charAt(array[i], d) + 1;
            aux[count[c]++] = array[i];
        }

        for (int i = low; i <= high; i++) {
            array[i] = aux[i - low];
        }

        for (int r = 0; r < 256; r++) {
            hybridSort(array, low + count[r], low + count[r + 1] - 1, d + 1);
        }
    }

    private static void threeWayQuickSort(String[] array, int low, int high, int d) {
        if (high <= low) return;

        int lt = low;
        int gt = high;
        int v = charAt(array[low], d);
        int i = low + 1;

        while (i <= gt) {
            int t = charAt(array[i], d);
            if (t < v) swap(array, lt++, i++);
            else if (t > v) swap(array, i, gt--);
            else i++;
        }

        threeWayQuickSort(array, low, lt - 1, d);
        if (v >= 0) threeWayQuickSort(array, lt, gt, d + 1);
        threeWayQuickSort(array, gt + 1, high, d);
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}