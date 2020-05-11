package com.zdd.jol;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {

        int pL = 8;
        Integer cL = 9;
        System.out.println(ClassLayout.parseInstance(pL).toPrintable());
        System.out.println(ClassLayout.parseInstance(cL).toPrintable());

        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        Integer[] aa = new Integer[2];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());
        Int anInt = new Int();
        System.out.println(ClassLayout.parseInstance(anInt).toPrintable());
        Pojo pojo = new Pojo();
        System.out.println(ClassLayout.parseInstance(pojo).toPrintable());

        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
    }

    static class Int {
        Integer a =9;
    }
    static class Pojo {
        public int a;
        public String b;
        public int c;
        public boolean d;
        private long e;
        public Object f;
        Pojo() { e = 10249L;}
    }

    public void testUnsafe() throws Exception {
        Class<?> unsafeClass = null;
        Unsafe unsafe = null;
        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
            final Field unsafeField = unsafeClass.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (Exception e) {
            // Ignore.
        }
        Pojo p = new Pojo();
        Field f = Pojo.class.getDeclaredField("e");
        long eOffset = unsafe.objectFieldOffset(f); // eOffset = 16
        if (eOffset > 0L) {
            long eVal = unsafe.getLong(p, eOffset);
            System.out.println(eVal); // 10249
        }
    }



}
