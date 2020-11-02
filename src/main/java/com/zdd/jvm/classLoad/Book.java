package com.zdd.jvm.classLoad;

public class Book {
    public static void main(String[] args) {
        System.out.println("Hello ShuYi.");
//        System.out.println(amount);
    }

    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }

    int price = 110;

    static {
        System.out.println("书的静态代码块");
    }

    static int amount = 112;
}
