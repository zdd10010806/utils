package com.zdd.algorithm;

import java.util.Stack;

public class TwoStackToQueue {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void add(Integer n) {
        stack1.push(n);
    }

    public Integer poll() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return null;
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

