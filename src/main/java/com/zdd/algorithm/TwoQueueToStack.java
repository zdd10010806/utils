package com.zdd.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class TwoQueueToStack {
    private Deque<Integer> deque1 = new ArrayDeque<>();
    private Deque<Integer> deque2 = new ArrayDeque<>();


    public void push(Integer n) {
        if (deque2.isEmpty()) {
            deque1.offer(n);
        } else {
            deque2.offer(n);
        }

    }

    public Integer pop() {
        if (deque1.isEmpty() && deque2.isEmpty()) {
            return null;
        }
        if (deque1.isEmpty() && !deque2.isEmpty()) {
            while (deque2.size() > 0) {
                deque1.add(deque2.poll());
            }
        }
        while (deque1.size() > 1) {
            deque2.add(deque1.poll());
        }
        return deque1.poll();
    }

    public boolean isEmpty() {
        if (deque1.isEmpty() && deque2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

