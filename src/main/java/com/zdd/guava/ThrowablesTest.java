package com.zdd.guava;

import com.google.common.base.Throwables;

import java.util.Arrays;

public class ThrowablesTest {

    public static void main(String[] args) {
        Throwable test1 = new ArithmeticException("test1");
        test1.initCause( new IndexOutOfBoundsException("IndexOutOfBoundsException"));

        Throwable test2 = new ArithmeticException("test2");
        test2.initCause( test1);

        Throwable test3 = new ArithmeticException("test3");
        test2.addSuppressed(test3);

        Arrays.stream(test2.getSuppressed()).forEach(x -> System.out.println(x.getMessage()));
//        test1.printStackTrace();
        System.out.println(test2.getCause());
        System.out.println(Throwables.getRootCause(test2));
        System.out.println(   Throwables.getCausalChain(test2));
        System.out.println(   Throwables.getStackTraceAsString(test2));

        System.out.println(" 1");


    }
}
