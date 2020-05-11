package com.zdd.algorithm;

public class BinarySearch {

    public static int binarySearch1(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;


        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] nums, int start, int end, int target) {
        if (nums == null || nums.length <= 0 || start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return binarySearch2(nums, start, mid - 1, target);
        } else {
            return binarySearch2(nums, mid + 1, end, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 5, 7, 9, 11};
        System.out.println(binarySearch1(nums, 1));
        System.out.println(binarySearch2(nums, 0, nums.length - 1, 1));

        int[] nums2 = {12, 23, 24, 25, 26, 27, 2, 3, 5};
        System.out.println(rotatedBinarySearch(nums2, 27, 6));
        System.out.println(rotatedBinarySearch(nums2, 27, true));
    }


    //已知旋转点
    public static int rotatedBinarySearch(int[] values, int target, int rotatedPoint) {
        if (values == null) {
            return -1;
        }
        boolean asc = values[rotatedPoint] < values[values.length - 1] ? true : false;
        int start = 0;
        int end = values.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int map = binarySearchIndexMap(mid, values.length, rotatedPoint);
            if (values[map] == target) {
                return map;
            } else if (values[map] < target) {
                if (asc) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            } else {
                if (asc) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int binarySearchIndexMap(int index, int length, int rotatedPoint) {
        return (rotatedPoint + index) % length;
    }


    public static int rotatedBinarySearch(int[] values, int target, boolean asc) {
        if (values == null) {
            return -1;
        }
        int start = 0;
        int end = values.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (values[mid] == target) {
                return mid;
            } else {
                if (asc) {  //升序排列
                    if (values[mid] >= values[start]) {
                        if (values[start] <= target && target <  values[mid] ) {
                            end = mid - 1;
                        } else {
                            start = mid + 1;
                        }
                    } else {
                        if (values[mid] < target &&   target <= values[end]) {
                            start = mid + 1;
                        } else {
                            end = mid - 1;
                        }
                    }
                } else {
                    if (values[mid] >= values[end]) {
                        if (values[mid] > target && values[end] <= target) {
                            start = mid + 1;
                        } else {
                            end = mid - 1;
                        }
                    } else {
                        if (values[mid] < target && values[start] >= target) {
                            end = mid - 1;
                        } else {
                            start = mid + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }


}
