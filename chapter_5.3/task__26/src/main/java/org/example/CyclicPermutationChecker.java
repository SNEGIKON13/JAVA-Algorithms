package org.example;

public class CyclicPermutationChecker {
    public static boolean isCyclicPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        String doubled = s1 + s1;
        return doubled.contains(s2);
    }

    public static void main(String[] args) {
        String str1 = "пальто";
        String str2 = "топаль";
        System.out.println(isCyclicPermutation(str1, str2));
    }
}