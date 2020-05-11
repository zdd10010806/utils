package com.zdd.algorithm;

public class RemoveDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,1,2,3,4,4,5,5,6};
        int length = removeDuplicate(nums);
        System.out.println(length);
        for (int i = 0; i < length; i++){
            System.out.println(nums[i]);
        }
    }
    public static  int removeDuplicate(int[] nums){
        if (nums == null || nums.length ==0){
            return 0;
        }
        int length = nums.length;
        if (length <=2){
            return length;
        }
        int cur =0;
        int aHead = 1;
        while (aHead < length){
            if (nums[aHead] != nums[cur]){
                nums[++cur] = nums[aHead];
            }
            aHead++;
        }
        return cur+1;
    }
}
