package com.zdd.java8;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUTest<K,V> extends LinkedHashMap<K,V>{
    private int capacity;
    public LRUTest(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
        this.capacity = initialCapacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUTest<Integer,Integer> map = new LRUTest<>(4, 0.75f, true);

        map.put(1, 1);

        map.put(2, 2);

        map.put(3, 3);

        map.get(2);

        map.put(4, 4);
        map.put(5, 5);
        map.get(3);
//        System.out.println(map);





    }
}
