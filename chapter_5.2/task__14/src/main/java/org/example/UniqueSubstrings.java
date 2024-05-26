package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueSubstrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String text = scanner.nextLine();
        System.out.println("Введите длину подстрок:");
        int L = scanner.nextInt();

        Set<String> uniqueSubstrings = new HashSet<>();

        for (int i = 0; i <= text.length() - L; i++) {
            String substring = text.substring(i, i + L);
            uniqueSubstrings.add(substring);
        }

        System.out.println("Количество уникальных подстрок длиной " + L + ": " + uniqueSubstrings.size());
    }
}