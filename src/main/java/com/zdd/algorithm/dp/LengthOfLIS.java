package com.zdd.algorithm.dp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LengthOfLIS {
//可能是连续的，也可能是非连续的，上升子序列
    public static int lengthOfLIS(int[] nums){
        if (nums == null || nums.length <=0 ){
            return 0;
        }
        if(nums.length ==1){
            return 1;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int max = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if ( nums[j] < nums[i]){
                    dp[i] =  Integer.max (dp[j] +1,dp[i]);
                }
            }
            System.out.println("i = " + (i) + ", dp[i] = " + dp[i]);
            if (dp[i] > max){
                max = dp[i];
            }

        }
        return  max;
    }

//    [1，9，5，9，3]

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        int[] nums = {1,9,5,9,3};
//        System.out.println(lengthOfLIS(nums));

        List<Integer> integers = new ArrayList<>();
        System.out.println(integers.size());
        integers.add(1);

        Field elementData = integers.getClass().getDeclaredField("elementData");
        elementData.setAccessible(true);
        int arrayLen = ((Object[]) elementData.get(integers)).length;
        System.out.println(integers.size());
        System.out.println(arrayLen);
        for (int i = 1; i < 10; i++) {
            integers.add(i+1);
        }
        arrayLen = ((Object[]) elementData.get(integers)).length;
        System.out.println(arrayLen);
        System.out.println(integers.size());
        integers.add(11);
        arrayLen = ((Object[]) elementData.get(integers)).length;
        System.out.println(arrayLen);
        System.out.println(integers.size());


    }
}
