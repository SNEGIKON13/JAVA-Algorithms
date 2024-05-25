package org.example;

import java.util.Arrays;

public class LSDSort {

    public static void main(String[] args) {
        String[] array = {
                "radix", "sort", "is", "a", "fast", "sorting", "algorithm",
                "for", "integers", "and", "strings", "of", "fixed", "lengths"
        };

        lsdSort(array);

        System.out.println("LSD Sorted: " + Arrays.toString(array));

        // Проверка с использованием встроенной сортировки Java
        String[] arrayCopy = {
                "radix", "sort", "is", "a", "fast", "sorting", "algorithm",
                "for", "integers", "and", "strings", "of", "fixed", "lengths"
        };
        Arrays.sort(arrayCopy);
        System.out.println("Java Sorted: " + Arrays.toString(arrayCopy));
    }

    public static void lsdSort(String[] array) {
        int maxLen = findMaxLength(array);
        for (int digit = maxLen - 1; digit >= 0; digit--) {
            countingSortByDigit(array, digit);
        }
    }

    private static int findMaxLength(String[] array) {
        int maxLen = 0;
        for (String s : array) {
            if (s.length() > maxLen) {
                maxLen = s.length();
            }
        }
        return maxLen;
    }

    private static void countingSortByDigit(String[] array, int digit) {
        int n = array.length;
        String[] output = new String[n];
        int[] count = new int[256];

        Arrays.fill(count, 0);

        for (String s : array) {
            char c = getCharAt(s, digit);
            count[c]++;
        }

        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = getCharAt(array[i], digit);
            output[count[c] - 1] = array[i];
            count[c]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    private static char getCharAt(String s, int digit) {
        if (digit < s.length()) {
            return s.charAt(digit);
        } else {
            return 0;
        }
    }
}