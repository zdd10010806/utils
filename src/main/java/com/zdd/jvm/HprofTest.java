package com.zdd.jvm;

public class HprofTest {

    public void slowMethod(){
        try {
            System.out.println("slowMethod");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void slowerMethod(){
        try {
            System.out.println("slowerMethod");

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fastMethod(){
        try {
            System.out.println("fastMethod");
            Thread.yield();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HprofTest hprofTest = new HprofTest();
        hprofTest.fastMethod();
        hprofTest.slowMethod();
        hprofTest.slowerMethod();
    }

}
