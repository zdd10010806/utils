package com.zdd.algorithm;

public class kmp {

    public static int[] getNexts(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 2; i < pattern.length(); i++) {
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i - 1)) {
                j = next[j];
            }
            if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int kmp(String str, String pattern) {
        int[] next = getNexts(pattern);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "ATGTGAGCTGGTGTGTGCFAA";
        String pattern = "GTGTGCF";
        System.out.println(kmp(str, pattern));
        System.out.println(KMP(str, pattern));
    }

    public static int[] getNext2(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;

        while (i < pattern.length()) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++j;
                next[i] = j;
                ++i;
            } else
                j = next[j];
        }
        return next;
    }

    public static int KMP(String str, String pattern) {
        int[] next = getNext2(pattern);
        int i = 0;
        int j = 0;

        while (i < str.length() && j < pattern.length()) {
            if (j == -1 || str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else
                j = next[j];
        }

        if (j == pattern.length())
            return i - j;
        else
            return -1;
    }


}
