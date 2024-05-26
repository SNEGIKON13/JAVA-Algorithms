package org.example;

public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;

        int X = 0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        int i;
        int j;
        int N = txt.length();
        int M = pat.length();

        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == M) return i - M;
        else return N;
    }

    public int count(String txt) {
        int count = 0;
        int N = txt.length();
        int M = pat.length();
        int i = 0;

        while (i <= N - M) {
            int pos = search(txt.substring(i));
            if (pos == txt.length()) break;
            count++;
            i += pos + 1;
        }

        return count;
    }

    public void searchAll(String txt) {
        int N = txt.length();
        int M = pat.length();
        int i = 0;

        while (i <= N - M) {
            int pos = search(txt.substring(i));
            if (pos == txt.length()) break;
            System.out.println("Pattern found at index " + (i + pos));
            i += pos + 1;
        }
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ab");
        String txt = "ababcabcab";
        System.out.println("Count: " + kmp.count(txt));
        kmp.searchAll(txt);
    }
}