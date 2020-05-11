package com.zdd.algorithm.linklist;

import com.zdd.algorithm.ListNode;

import java.util.Stack;

public class LinkListIntersect {

    public static ListNode reverseLinkList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2 = p1.next;
        ListNode p3;
        while (p2 != null) {
            p3 = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }
        head.next = null;
        return p1;
    }


    /**
     * 判断单链表是否相交
     */
    public static boolean isIntersect(ListNode head1, ListNode head2) {
        ListNode p1 = head1;
        ListNode p2 = head2;
        while (p1.next != null) {
            p1 = p1.next;

        }
        ListNode last1 = p1;
        while (p2.next != null) {
            p2 = p2.next;
        }
        ListNode last2 = p2;
        if (last1 == last2) {
            return true;
        }

        return false;


    }


    public static ListNode firstIntersect(ListNode head1, ListNode head2) {
        ListNode p1 = head1;
        ListNode p2 = head2;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (p1.next != null) {
            stack1.push(p1);
            p1 = p1.next;

        }
        while (p2.next != null) {
            stack2.push(p2);
            p2 = p2.next;
        }

        ListNode first = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode listNode1 = stack1.pop();
            ListNode listNode2 = stack2.pop();
            if (listNode1 == listNode2) {
                first = listNode1;
            }
        }

        return first;


    }

    public static int len(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            len++;
        }
        return len;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(2, listNode1);
        ListNode listNode3 = new ListNode(3, listNode2);


        ListNode listNode4 = new ListNode(4, listNode3);
        ListNode listNode5 = new ListNode(5, listNode4);

        ListNode listNode6 = new ListNode(6, listNode5);

        ListNode listNode7 = new ListNode(7, listNode5);
        ListNode listNode8 = new ListNode(8, listNode7);
        ListNode listNode9 = new ListNode(9, listNode8);

        ListNode head1 = listNode6;
        while (head1 != null) {
            System.out.print(head1.val + "-->");
            head1 = head1.next;
        }
        System.out.println();
        System.out.println("==============");
        ListNode head2 = listNode9;
        while (head2 != null) {
            System.out.print(head2.val + "-->");
            head2 = head2.next;
        }

        boolean intersect = isIntersect(listNode6, listNode9);
        System.out.println("==============");
        System.out.println(intersect);

        ListNode listNode = firstIntersect(listNode6, listNode9);
        if (listNode != null) {
            System.out.println(listNode.val);
        }

        System.out.println(len(listNode6));
        System.out.println(len(listNode9));
    }
}
