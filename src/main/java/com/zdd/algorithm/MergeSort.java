package com.zdd.algorithm;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {5, 4, 9, 0, 5, 7, 8, 2, 4, 6, 1};
        System.out.println(Arrays.toString(nums));
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
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
        ListNode listNode = mergeSortLink(listNode7);
        ListNode newHead = listNode;
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

    }

    public static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) /2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);

    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int p = 0;
        while (p1 <= mid && p2 <= end) {
            if (nums[p1] <= nums[p2]) {
                tmp[p++] = nums[p1++];
            } else {
                tmp[p++] = nums[p2++];
            }
        }
        while (p1 <= mid) {
            tmp[p++] = nums[p1++];
        }
        while (p2 <= end) {
            tmp[p++] = nums[p2++];
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[start + i] = tmp[i];
        }
    }

    public static ListNode mergeSortLink(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //获取中间结点
        ListNode mid = getMid(head);
        ListNode right = mid.next;
        mid.next = null;
        //合并
        return mergeLink(mergeSortLink(head), mergeSortLink(right));
    }

    private static ListNode mergeLink(ListNode head1, ListNode head2) {
        ListNode p1 = head1;
        ListNode p2 = head2;
        ListNode head;
        //得到头节点的指向
        if (head1.val < head2.val) {
            head = head1;
            p1 = p1.next;
        } else {
            head = head2;
            p2 = p2.next;
        }

        ListNode p = head;
        //比较链表中的值
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return head;
    }

    private static ListNode getMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

}