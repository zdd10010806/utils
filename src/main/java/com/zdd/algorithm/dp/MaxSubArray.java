package com.zdd.algorithm.dp;

public class MaxSubArray {

    //找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
    public static  int maxSubArray(int[] nums){

        if (nums == null || nums.length <=0 ){
            return 0;
        }
        if(nums.length ==1){
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[1] = nums[0];
       int max = dp[1];
        for (int i = 2; i < n+1; i++) {
            if (dp[i -1] > 0 ){
                dp[i] =  dp[i -1] + nums[i-1];
            } else {
                dp[i] = nums[i-1];
            }
            if (dp[i] > max){
                max = dp[i];
            }

        }
        return  max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
