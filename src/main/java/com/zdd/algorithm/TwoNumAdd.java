package com.zdd.algorithm;

import java.util.Stack;

public class TwoNumAdd {

    public static void main(String[] args) {
        ListNode listNode6 = new ListNode(6, null);
        ListNode listNode5 = new ListNode(5, listNode6);
        ListNode listNode4 = new ListNode(4, listNode5);

        ListNode listNode3 = new ListNode(7, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);


        print(AddTwoNum(listNode1, listNode4));
        print2(AddTwoNum2(listNode1, listNode4));

    }

    public static ListNode AddTwoNum(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode res = new ListNode(-1);
        ListNode tmp = res;
        int jinWei = 0;
        while (head1 != null && head2 != null) {
            int sum = head1.val + head2.val + jinWei;
            res.next = new ListNode(sum % 10);
            res = res.next;
            jinWei = sum / 10;
            head1 = head1.next;
            head2 = head2.next;
        }
        while (head1 != null) {
            int sum = head1.val + jinWei;
            res.next = new ListNode(sum % 10);
            res = res.next;
            jinWei = sum / 10;
            head1 = head1.next;
        }

        while (head2 != null) {
            int sum = head2.val + jinWei;
            res.next = new ListNode(sum % 10);
            res = res.next;
            jinWei = sum / 10;
            head2 = head2.next;
        }
        if (jinWei == 1) {
            res.next = new ListNode(1);
        }
        return tmp.next;
    }

    public static Stack<Integer> AddTwoNum2(ListNode head1, ListNode head2) {
        Stack<Integer> s1= new Stack<>();
        Stack<Integer> s2= new Stack<>();
        ListNode h1 = head1;
        ListNode h2 = head2;
        while (h1!=null){
            s1.push(h1.val);
            h1 = h1.next;
        }
        while (h2!=null){
            s2.push(h2.val);
            h2 = h2.next;
        }
        if (s1.isEmpty()) {
            return s2;
        }
        if (s2.isEmpty()) {
            return s1;
        }

        Stack<Integer> s3= new Stack<>();
        int jinWei = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int sum =s1.pop() + s2.pop() + jinWei;
            s3.push(sum % 10);
            jinWei = sum / 10;
        }
        while (!s1.isEmpty()) {
            int sum =s1.pop() + jinWei;
            s3.push(sum % 10);
            jinWei = sum / 10;
        }
        while (!s2.isEmpty()) {
            int sum =s2.pop() + jinWei;
            s3.push(sum % 10);
            jinWei = sum / 10;
        }


        if (jinWei == 1) {
            s3.push(1);
        }
        return s3;
    }


    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println();
    }
  public static void print2(Stack<Integer> s) {
        while (!s.isEmpty()) {
            System.out.print(s.pop() + "\t");
        }
        System.out.println();
    }


}
