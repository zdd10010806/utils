package com.zdd.algorithm;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString {
    public static int lengthOfLongestSubString(String s) {
        if (s == null || s.length() ==0){
            return 0;
        }
        int left = 0;
        int right = 0;
        int len = s.length();
        int[] flag = new int[256];
        int maxLen = 0;
        int start = 0;
        while (left < len) {
            if (right < len && flag[s.charAt(right)] ==0){
                flag[s.charAt(right++)] = 1;
            }else {
                if (right - left  > maxLen){
                    maxLen = right - left;
                    start = left;
                }
                flag[s.charAt(left++)] = 0;
            }
        }
        System.out.println(start);
        System.out.println(s.substring(start,start + maxLen));
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abbcc";
        System.out.println(lengthOfLongestSubString(s));
        System.out.println(lengthOfLongestSubString2(s));
        System.out.println(lengthOfLongestSubstring3(s));
        System.out.println(lengthOfLongestSubstring4(s));
    }

    public static int lengthOfLongestSubString2(String s) {
        if (s == null || s.length() ==0){
            return 0;
        }
        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> map = Maps.newHashMap();
       for (int i = 0; i< s.length();i++){
           char c = s.charAt(i);
           if (map.containsKey(c)){
//               left = left  > map.get(c)  ? left : map.get(c);
               left =  map.get(c) ;
               maxLen = maxLen > (i-left +1) ?maxLen : (i-left+1 );
           }
           map.put(c,i +1) ;
       }
       if (maxLen==0){
           return s.length();
       }
        return maxLen;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[256];
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
    public static int lengthOfLongestSubstring4(String s) {

        int n = s.length(), ans = 0;
        int[] freq = new int[256];
        int left = 0, right = -1;
        while (left < n) {
            if (right + 1 < n && freq[s.charAt(right + 1)] == 0) {
                right++;
                freq[s.charAt(right)]++;
            } else {
                freq[s.charAt(left)]--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
