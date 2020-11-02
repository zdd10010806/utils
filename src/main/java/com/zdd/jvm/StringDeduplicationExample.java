package com.zdd.jvm;

import java.util.ArrayList;
import java.util.List;

public class StringDeduplicationExample {

    public static List<String> myStrings = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int counter = 0; counter < 200; ++counter) {
            for (int secondCounter = 0; secondCounter < 1000; ++secondCounter) {       // Add it 1000 times.
                myStrings.add(("Hello World-" + counter));
            }
            System.out.println("Hello World-" + counter + " has been added 1000 times");
        }
    }
}
