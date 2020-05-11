package com.zdd.classloader;


/**
 * -Xbootclasspath/a   将classpath添加在核心class搜索路径后面
 */
public class FindClassOrder {
    public static void main(String[] args) {
        HelloLoader loader = new HelloLoader();
        loader.print();
    }
}
