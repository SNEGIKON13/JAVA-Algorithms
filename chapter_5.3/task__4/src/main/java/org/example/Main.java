package org.example;

public class Main {
    public static int findConsecutiveSpaces(String txt, int m) {
        int n = txt.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (txt.charAt(i) == ' ') {
                count++;
                if (count == m) {
                    return i - m + 1;
                }
            } else {
                count = 0;
            }
        }

        return n;
    }

    public static void main(String[] args) {
        String txt = "Example text with   multiple  spaces.";
        int m = 3;
        int position = findConsecutiveSpaces(txt, m);
        System.out.println("The position is: " + position);
    }
}