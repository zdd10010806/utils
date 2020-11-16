package com.zdd.jvm.classLoad;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//解析 首先对应上面的main方法 会发生初始化，
// 发生初始化说明了经历了加载 - 验证 - 准备 - 解析 - 初始化(5个阶段)。
// 而准备阶段  是初始化static域为默认值 。
// 初始化阶段是静态域 (static代码块) > 变量 >  构造方法。
// 因此先执行static Table table = new Table() ;
// 碰到new实例，又要初始化Table类。
public class StaticInitialization {
    public static void main(String[] args) {
//        System.out.println("Creating new Cupboard() in main"); //c : 10 Creating new Cupboard() in main
//        new Cupboard(); //d 注意Cupboard类已经初始化过一次，所以 static域(静态变量)，static代码块只初始化一次
//        System.out.println("Creating new Cupboard() in main"); //f 13 Creating new Cupboard() in main
//        new Cupboard(); //g 重复d一样的过程 13 14 15
//        table.f2(1); //h 16 f2(1)
//        cupboard.f3(1); // i 17 f3(1)
        List<Integer> objects = Lists.newArrayList();
        List<Integer> objects2 = Lists.newArrayList();
        objects.add(1);
        objects.add(1);
        objects.add(2);
        objects.add(3);
       Iterables.removeIf(objects, x -> x == 1);


        System.out.println(objects);
        objects.remove(new Integer(1));
        objects.removeAll(Collections.singletonList(1));
        System.out.println(objects);
        objects.remove(1);
        System.out.println(objects);



    }

    static Table table = new Table(); //a: 最先执行，初始化Table类
    static Cupboard cupboard = new Cupboard();//b: 接着执行 ，初始化Cupboard类
}

class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");
    }
}

class Table {  //静态域 (static代码块) > 变量 >  构造方法
    static Bowl bowl1 = new Bowl(1); // 1 打印Bowl(1)

    Table() {
        System.out.println("Table()"); //3 Table()
        bowl2.f1(1); //4 f1(1)
    }

    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }

    static Bowl bowl2 = new Bowl(2); // 2 打印Bowl(2)
}

class Cupboard { //静态域 (static代码块) > 变量 >  构造方法
    Bowl bowl3 = new Bowl(3); //7 Bowl(3) | 10 Bowl(3) （static域只初始化一次，所以只有bolw3才会再次赋值，因为在堆中又有一个对象啦）
    static Bowl bowl4 = new Bowl(4); // 5 Bowl(4)

    Cupboard() {
        System.out.println("Cupboard()"); //8 Cupboard() | 11 Cupboard()
        bowl4.f1(2); // 9 f1(2) | 12 f1(2)
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }

    static Bowl bowl5 = new Bowl(5); // 6  Bowl(5)
}


