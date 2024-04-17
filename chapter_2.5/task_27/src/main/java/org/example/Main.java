package org.example;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите строку чисел разделяемых пробелом:");
        String str = input.nextLine();
        String[] strs = str.split(" ");
        Integer[] array = new Integer[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Integer.parseInt(strs[i]);
        }
        int[] index = ParallelArraySort.indirectSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(index[i] + " ");
        }
    }
}
