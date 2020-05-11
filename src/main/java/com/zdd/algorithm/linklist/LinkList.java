package com.zdd.algorithm.linklist;

import com.zdd.algorithm.ListNode;

public class LinkList {

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
     * 判断单链表是否存在环
     *
     * @param head
     * @return
     */
    public static boolean isLoopList(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        //使用快慢指针，慢指针每次向前一步，快指针每次两步
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            //两指针相遇则有环
            if (slowPointer == fastPointer) {
                return true;
            }
        }
        return false;
    }


    //  定理：碰撞点到连接点的距离=头指针到连接点的距离
    //  当单链表中没有环时返回null，有环时返回环的入口结点
    public static ListNode searchEntranceNode(ListNode L) {
        ListNode slow = L;//p表示从头结点开始每次往后走一步的指针
        ListNode fast = L;//q表示从头结点开始每次往后走两步的指针
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;//p与q相等，单链表有环
        }
        if (fast == null || fast.next == null)
            return null;

        slow = L;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //求单链表环的长度
    public static  int circleLength(ListNode L) {
        ListNode p = searchEntranceNode(L);//找到环的入口结点
        if (p == null)
            return 0;//不存在环时，返回0
        ListNode q = p.next;
        int length = 1;
        while (p != q) {
            length++;
            q = q.next;
        }
        return length;//返回环的长度
    }

    //   1. 已知单链表的头指针，查找到倒数第K个节点
//    一个快指针P和慢指针Q,先让P指针走到K个节点位置，然后Q指针从头指针开始和P一起移动，当P移动到尾部的时候，那么此时Q节点所在的位置就是倒数第K个节点。

    public static  ListNode kNode(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        int i = 1;
        while (i < k && fast != null) {
            fast = fast.next;
            i++;

        }
        if (fast == null) {
            return null;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(5, null);
        ListNode listNode2 = new ListNode(4, listNode1);
        ListNode listNode3 = new ListNode(1, listNode2);


        ListNode listNode4 = new ListNode(4, listNode3);
        ListNode listNode5 = new ListNode(3, listNode4);
        ListNode listNode6 = new ListNode(1, listNode5);

        ListNode listNode7 = new ListNode(6, listNode6);

        ListNode head = listNode7;
        while (head != null) {
            System.out.print(head.val + "-->");
            head = head.next;
        }
        System.out.println();
        System.out.println("==============");
        ListNode listNode = reverseLinkList(listNode7);
        ListNode newHead = listNode;
        while (newHead != null) {
            System.out.print(newHead.val + "-->");
            newHead = newHead.next;
        }
    }
}
