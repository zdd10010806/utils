package com.zdd.algorithm;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
//        int[] nums = {5, 4, 9, 0, 5, 7, 8, 2, 4, 6, 1};
//        System.out.println(Arrays.toString(nums));
//        quickSort(nums, 0, nums.length - 1);
//        System.out.println(Arrays.toString(nums));
//
//        int[] nums2 = {5, 4, 9, 0, 5, 7, 8, 2, 4, 6, 1};
//
//        System.out.println(Arrays.toString(nums2));
//        quickSort2(nums2, 0, nums2.length - 1);
//        System.out.println(Arrays.toString(nums2));

        ListNode listNode1 = new ListNode(5, null);
        ListNode listNode2 = new ListNode(4, listNode1);
        ListNode listNode3 = new ListNode(1, listNode2);


        ListNode listNode4 = new ListNode(4, listNode3);
        ListNode listNode5 = new ListNode(3, listNode4);
        ListNode listNode6 = new ListNode(1, listNode5);

        ListNode listNode7 = new ListNode(6, listNode6);

        ListNode head = listNode7;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
        System.out.println("==============");
        ListNode listNode = sortListLink(listNode7);
        ListNode newHead = listNode;
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partition(nums, start, end);
        quickSort(nums, start, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);

    }

    //挖坑法
    private static int partition(int[] nums, int start, int end) {
        int left = start;
        int index = start;
        int right = end;
        int pivot = nums[start];
        while (right >= left) {
            while (right >= left) {
                if (nums[right] < pivot) {
                    nums[left] = nums[right];
                    left++;
                    index = right;
                    break;
                }
                right--;
            }

            while (right >= left) {
                if (nums[left] > pivot) {
                    nums[right] = nums[left];
                    right--;
                    index = left;
                    break;
                }
                left++;

            }
        }
        nums[index] = pivot;
        return index;
    }

    public static void quickSort2(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partition2(nums, start, end);
        quickSort2(nums, start, pivotIndex - 1);
        quickSort2(nums, pivotIndex + 1, end);

    }

    //指针交换法
    private static int partition2(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int pivot = nums[start];
        while (right > left) {
            while (right > left && nums[right] > pivot) {
                right--;
            }
            while (right > left && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }

        int tmp = nums[start];
        nums[start] = nums[left];
        nums[left] = tmp;
        return left;
    }


    public static ListNode sortListLink(ListNode head) {
        //采用快速排序
        quickSortLink(head, null);
        return head;
    }

    public static void quickSortLink(ListNode head, ListNode end) {
        if (head != end) {
            ListNode node = partion(head, end);
            quickSortLink(head, node);
            quickSortLink(node.next, end);
        }
    }

    public static ListNode partion(ListNode head, ListNode end) {
        ListNode p1 = head;
        ListNode p2 = head.next;

        int pivot = head.val;

        //走到末尾才停
        while (p2 != end) {

            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.val < pivot) {

                p1 = p1.next;

                if (p1 != p2) {
                    swap(p1,p2);
                }

            }
            p2 = p2.next;
        }

        //当有序时，不交换p1和key值
        if (p1 != head) {
            int temp = p1.val;
            p1.val = head.val;
            head.val = temp;
        }
        return p1;
    }


    public static void swap (ListNode p1, ListNode p2){
        int temp = p1.val;
        p1.val = p2.val;
        p2.val = temp;
    }


}
