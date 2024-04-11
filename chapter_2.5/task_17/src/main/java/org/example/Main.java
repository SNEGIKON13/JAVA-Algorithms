package org.example;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите строку чисел разделяемых пробелом:");
        String str = input.nextLine();
        String[] strs = str.split(" ");
        CheckStructure[] structure = new CheckStructure[strs.length];
        for (int i = 0; i < strs.length; i++) {
            structure[i] = new CheckStructure(Integer.parseInt(strs[i]), i);
        }
        System.out.println("Сортировка является устойчивой: "
                + CheckStructure.check(structure));
    }
}
