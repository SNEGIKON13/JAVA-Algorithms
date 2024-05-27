package org.example;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {
    public static int indexOf(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;
        Map<Character, Integer> badChar = preprocessBadCharacter(pattern);

        int s = 0;
        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pattern[j] == text[s + j]) {
                j--;
            }
            if (j < 0) {
                return s;
            } else {
                s += Math.max(1, j - badChar.getOrDefault(text[s + j], -1));
            }
        }
        return -1;
    }

    private static Map<Character, Integer> preprocessBadCharacter(char[] pattern) {
        Map<Character, Integer> badChar = new HashMap<>();
        for (int i = 0; i < pattern.length; i++) {
            badChar.put(pattern[i], i);
        }
        return badChar;
    }

    public static void main(String[] args) {
        char[] text = "hello world".toCharArray();
        char[] pattern = "world".toCharArray();
        int index = indexOf(text, pattern);
        System.out.println("Index: " + index);
    }
}