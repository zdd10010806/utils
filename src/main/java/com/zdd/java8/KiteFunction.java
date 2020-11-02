package com.zdd.java8;

@FunctionalInterface
public interface KiteFunction<T, R, S> {

    /**
     * 定义一个双参数的方法
     *
     * @param t
     * @param s
     * @return
     */
    R run(T t, S s);
}

