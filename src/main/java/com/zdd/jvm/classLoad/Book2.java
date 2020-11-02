package com.zdd.jvm.classLoad;

public class Book2 {
    public static void main(String[] args) {
        staticFunction();
        System.out.println("price=" + book.price + ",amount=" + amount + ",amountFinal=" + book.amountFinal + ",amountStaticFinal=" + amountStaticFinal  );
    }
    static Book2 book = new Book2();
    static {
        System.out.println("3.书的静态代码块");
    }
    {
        System.out.println("1.书的普通代码块");
    }
    Book2() {
        System.out.println("2.书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount + ",amountFinal=" + amountFinal + ",amountStaticFinal=" + amountStaticFinal  );
    }
    public static void staticFunction() {
        System.out.println("书的静态方法");
    }
    int price = 110;
    static int amount = 111;
    final int amountFinal = 112;
    static final int amountStaticFinal = 113;
}
