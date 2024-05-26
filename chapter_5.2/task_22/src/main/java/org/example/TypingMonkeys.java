package org.example;

import java.util.HashSet;
import java.util.Random;

public class TypingMonkeys {
    public static void main(String[] args) {
        final double p = 0.03;  // Вероятность добавления новой буквы
        final int simulations = 100000;  // Количество симуляций
        HashSet<String> uniqueWords = new HashSet<>();
        int[] lengthFrequency = new int[100];  // Массив для хранения частот длин слов

        Random random = new Random();

        for (int i = 0; i < simulations; i++) {
            StringBuilder word = new StringBuilder();
            while (random.nextDouble() < p) {
                char letter = (char) ('a' + random.nextInt(26));
                word.append(letter);
            }
            String newWord = word.toString();
            if (!newWord.isEmpty() && uniqueWords.add(newWord)) {
                int length = newWord.length();
                if (length < lengthFrequency.length) {
                    lengthFrequency[length]++;
                }
            }
        }

        System.out.println("Length\tFrequency");
        for (int i = 1; i < lengthFrequency.length; i++) {
            if (lengthFrequency[i] > 0) {
                System.out.println(i + "\t" + lengthFrequency[i]);
            }
        }
    }
}