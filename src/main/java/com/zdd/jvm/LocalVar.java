package com.zdd.jvm;

public class LocalVar {

    public static void localVarGc1() {
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    public static void localVarGc2() {
        byte[] a = new byte[6 * 1024 * 1024];
        a = null;
        System.gc();
    }

    public static void localVarGc3() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    public static void localVarGc4() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    public static void localVarGc5() {
        localVarGc1();
        System.gc();
    }

    public static void main(String[] args) {
        localVarGc2();

//        System.gc();
    }

}
