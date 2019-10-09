package com.zdd.beanUtils;

import com.zdd.beans.User;
import org.springframework.cglib.beans.BeanCopier;

/**
 * get/set          22ms              setter/getter调用
 * * BeanCopier       22ms              基于cglib,修改字节码
 * * PropertyUtils    922ms                反射
 * * BeanUtils       12983ms               反射
 * * 凡是和反射相关的操作，基本都是低性能的。凡是和字节码相关的操作，基本都是高性能的。
 */

public class BeanCopierUtil {

    public static void copy(Object srcObj, Object destObj) {
        BeanCopier copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
        copier.copy(srcObj, destObj, null);
    }


    public static void main(String[] args) {

        User u1 = new User();
        u1.setId(1l);
        u1.setName("chenpi1");
        u1.setState(true);
        User u2 = new User();

        copy(u1,u2);

        System.out.println(u1);
        System.out.println(u2);

    }

}
