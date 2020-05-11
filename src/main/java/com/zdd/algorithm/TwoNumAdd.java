package com.zdd.algorithm;

public class TwoNumAdd {

    public static void main(String[] args) {
        ListNode listNode6 = new ListNode(6, null);
        ListNode listNode5 = new ListNode(5, listNode6);
        ListNode listNode4 = new ListNode(4, listNode5);

        ListNode listNode3 = new ListNode(7, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);


        print(AddTwoNum(listNode1, listNode4));

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


    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println();
    }


}
