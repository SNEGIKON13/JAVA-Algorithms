package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String[] strs = scanner.nextLine().split(" ");
        list.addAll(Arrays.asList(strs));
        System.out.println("Введите Key:");
        String key = scanner.nextLine();
        list.removeKey(key);
        for (String z : list) {
            System.out.println(z);
        }
    }
}