package com.zdd.algorithm;

public class SwapPairs {

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

        print(swapPairs(listNode1));

        print(swapPairsRecursive(listNode1));

    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode curr = dummy;
        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;

            // swap two nodes
            first.next = second.next;
            second.next = first;
            curr.next = second;

            // update to next iteration
            curr = first ;
        }
        return dummy.next;
    }

    public static ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode node = head.next;
        head.next = swapPairsRecursive(node.next);
        node.next = head;
        return node;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println();
    }


}
