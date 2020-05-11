package com.zdd.algorithm;

public class MergeKList {
    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(5, null);
        ListNode listNode2 = new ListNode(4, listNode1);
        ListNode head1 = new ListNode(1, listNode2);


        ListNode listNode3 = new ListNode(4, null);
        ListNode listNode4 = new ListNode(3, listNode3);
        ListNode head2 = new ListNode(1, listNode4);

        ListNode listNode5 = new ListNode(6, null);
        ListNode head3 = new ListNode(2, listNode5);

        ListNode[] listNodes = {head1, head2, head3};
        ListNode newHead = mergeKList(listNodes);
        while (newHead != null) {
            System.out.print (newHead.val + "\t");
            newHead = newHead.next;
        }
    }


    private static ListNode mergeKList(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0,   lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode node1 = merge(lists, left, mid);
        ListNode node2 = merge(lists, mid + 1, right);
        return  merge(node1,node2);

    }

    private static ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null){
            return  node1 == null ? node2 : node1;
        }
        if (node1.val < node2.val){
            node1.next = merge(node1.next,node2);
            return node1;
        }else {
            node2.next = merge(node1,node2.next);
            return node2;
        }
    }
}
