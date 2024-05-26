package org.example;

public class SubstringSearch {
    public static int indexOf(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text[i + j] == pattern[j]) {
                j++;
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] text = "hello world".toCharArray();
        char[] pattern = "world".toCharArray();
        int index = indexOf(text, pattern);
        System.out.println("Index: " + index);
    }
}