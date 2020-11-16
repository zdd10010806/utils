package com.zdd.algorithm.linklist;

import lombok.Data;

public class Morris {

    @Data
    static class Node {
        private Node left;
        private Node right;
        int value;
    }

    /**
     * 记作当前节点为cur。
     * 1. 如果cur无左孩子，cur向右移动（cur=cur.right）
     * 2. 如果cur有左孩子，找到cur左子树上最右的节点，记为mostright
     *     如果mostright的right指针指向空，让其指向cur，cur向左移动（cur=cur.left）
     *     如果mostright的right指针指向cur，让其指向空，cur向右移动（cur=cur.right）
     */

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        //没有左子树或者添加右指针时打印当前节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {  //有左子树
                while (mostRight.right != null && mostRight.right != cur) { // 找到左子树的最后节点
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {//当前节点作为左子树的最后节点的后继节点
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;// 恢复指针
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;//没有左子树或者恢复节点右指针时
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");//没有左子树或者恢复节点右指针时
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }
    public static void printEdge(Node node){
        Node tail =reverseEdge(node);
        Node cur = tail;
        while (cur != null ){
            System.out.print(cur.value+" ");
            cur =cur.right;
        }
        reverseEdge(tail);
    }
    public static Node reverseEdge(Node node){
        Node pre = null;
        Node next = null;
        while (node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

}
