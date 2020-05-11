package com.zdd.classloader;

public class PrintClassLoaderTree {

    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        while (classLoader!=null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
