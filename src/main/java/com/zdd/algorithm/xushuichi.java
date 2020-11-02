package com.zdd.algorithm;

import java.util.Arrays;

public class xushuichi {
    public static int calLine(int[] nums){
        int left = 0;
        int right = nums.length -1;
        while (left < nums.length) {
            if (nums[left] > 0){
                break;
            }
            left++;
        }
        while ( right > 0) {
            if (nums[right] > 0){
                break;
            }
            right--;
        }
        int count =0;
        while (left < right) {
            if (nums[left] == 0){
                count++;
            }
            left++;
        }
        return count;

    }

    public static boolean decLine(int[] nums){
        boolean isAllZero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                nums[i]--;
                isAllZero = false;
            }

        }
        return isAllZero;
    }
    public static void main(String[] args) {

        int[] nums ={5,2,1,4,3};
        int[] nums2 ={5,2,1,6,3,4,2};
        int sum =0;
        while(true){
            int count = calLine(nums2);
            sum += count;
            if (decLine(nums2)){
                break;
            }
        }
        System.out.println(sum);
    }

}
