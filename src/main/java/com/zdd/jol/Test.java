package com.zdd.jol;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
//        System.out.println(RamUsageEstimator.sizeOf(new int[2]));
//        System.out.println(RamUsageEstimator.sizeOf(new Integer[2]));
//        System.out.println(RamUsageEstimator.shallowSizeOf(new int[2]));
//        System.out.println(RamUsageEstimator.shallowSizeOf(new Integer[2]));
//        System.out.println(RamUsageEstimator.shallowSizeOfInstance(int.class));
//        System.out.println(RamUsageEstimator.shallowSizeOfInstance(Integer.class));
//
//        int i  =1;
//
//
//        Integer integer = new Integer(2);
//        System.out.println(RamUsageEstimator.sizeOf(i));
//        System.out.println(RamUsageEstimator.sizeOf(integer));
//        System.out.println(RamUsageEstimator.shallowSizeOf(i));
//        System.out.println(RamUsageEstimator.shallowSizeOf(integer));
//
//        System.out.println(Object.class.isAssignableFrom(Integer.class));
        System.out.println(Object.class.isAssignableFrom(int.class));
        System.out.println(Object.class.isAssignableFrom(int[].class));

        B b = new B();
        System.out.println(RamUsageEstimator.shallowSizeOf(b));
        System.out.println(RamUsageEstimator.sizeOf(b));
        System.out.println(ClassLayout.parseInstance(b).toPrintable());

        C c = new C();

        System.out.println(RamUsageEstimator.shallowSizeOf(c));
        System.out.println(RamUsageEstimator.sizeOf(c));
        System.out.println(ClassLayout.parseInstance(c).toPrintable());

        Integer[] aa = new Integer[2];
        aa[0] = 1;
        aa[1] = 2;
        System.out.println("============");
        System.out.println(RamUsageEstimator.shallowSizeOf(aa));
        System.out.println(RamUsageEstimator.sizeOf(aa));
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());

        Main.Int anInt = new Main.Int();

        System.out.println(RamUsageEstimator.shallowSizeOf(anInt));
        System.out.println(RamUsageEstimator.sizeOf(anInt));
    }
}
