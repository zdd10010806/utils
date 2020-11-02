package com.zdd.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class OneQueueToStack<T> {
    private Queue<T> queue = new LinkedList<>();

    public void push(T data) {
        queue.offer(data);
    }

    public T pop() {
        if (queue.isEmpty())
            return null;
        else {
            int size = queue.size();
            for (int i = 0; i < size - 1; i++)
                queue.offer(queue.poll());
            return queue.poll();
        }
    }
}
