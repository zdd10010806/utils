package com.zdd.classloader;

public class HelloLoader {

    public static int a = 10;
    public final  int b = 5;
    public   int c = 15;
    public void print(){
        System.out.println("I am in App ClassLoader");
    }
}
