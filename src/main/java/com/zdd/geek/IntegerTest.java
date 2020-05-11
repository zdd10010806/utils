package com.zdd.geek;

public class IntegerTest {

    public static void main(String[] args) {

//        test01(127);
//        test01(128);
//        test01(null);


        Integer i01 = 59;
        int i02 = 59;
        Integer i03 = Integer.valueOf(59);
        Integer i04 = new Integer(59);

        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
    }

    private static void test01(Integer i) {
        System.out.println(i == 128);
    }

    private static void test02(int i) {
        System.out.println(i);
    }
}
