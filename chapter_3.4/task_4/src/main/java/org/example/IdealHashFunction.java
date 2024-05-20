package org.example;

import java.util.HashSet;

public class IdealHashFunction {
    /**
     * Находит значения a и M для идеальной хеш-функции
     * @param keys ключи для хеширования
     * @return массив, содержащий значения a и M
     */
    public static int[] findIdealHashFunction(String[] keys) {
        int[] result = new int[2];
        boolean found = false;
        int a = 2;
        int M = keys.length + 1;

        while (!found) {
            HashSet<Integer> hashes = new HashSet<>();

            for (String key : keys) {
                int hash = (a * getAlphabetIndex(key)) % M;
                if (hashes.contains(hash)) {
                    // Коллизия найдена, переходим к следующему значению a
                    break;
                }
                hashes.add(hash);
            }

            if (hashes.size() == keys.length) {
                // Найдена идеальная хеш-функция
                result[0] = a;
                result[1] = M;
                found = true;
            } else {
                // Переходим к следующему значению a и M
                a++;
                M++;
            }
        }
        return result;
    }

    /**
     * Возвращает индекс буквы в алфавите (A=1, B=2, и т.д.)
     * @param letter буква
     * @return индекс буквы в алфавите
     */
    private static int getAlphabetIndex(String letter) {
        return letter.toUpperCase().charAt(0) - 'A' + 1;
    }

    public static void main(String[] args) {
        String[] keys = {"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        int[] idealHashFunction = findIdealHashFunction(keys);
        System.out.println("a = " + idealHashFunction[0]);
        System.out.println("M = " + idealHashFunction[1]);
    }
}