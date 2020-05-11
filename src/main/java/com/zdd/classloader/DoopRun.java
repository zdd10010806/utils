package com.zdd.classloader;

import java.lang.reflect.Method;

public class DoopRun {
    public static void main(String args[]) {
        while(true){
            try{
                MyClassLoader myClassLoader = new MyClassLoader("/Users/zrh");
                Class<?> cls = myClassLoader.findClass("com.zdd.classloader.HelloLoader");
                Object demo = cls.newInstance();

                Method m = demo.getClass().getMethod("print");
                m.invoke(demo);
                Thread.sleep(10000);
            }catch(Exception e){
                System.out.println("not find");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                }
            }
        }
    }
}
