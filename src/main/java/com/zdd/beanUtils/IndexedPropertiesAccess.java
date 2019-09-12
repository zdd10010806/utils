package com.zdd.beanUtils;


import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class IndexedPropertiesAccess {


    public static void main(String[] args)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 初始工作
        IndexedBean indexedBean = new IndexedBean();
        List<Employee> employeeList = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setLastName("Chen");
        Employee e2 = new Employee();
        e2.setLastName("Wang");
        employeeList.add(e1);
        employeeList.add(e2);
        indexedBean.setEmployeeList(employeeList);
        indexedBean.setIntArr(new Integer[]{0, 1, 2});

        // API测试
        int index0 = 0;
        String name0 = "employeeList[" + index0 + "]";
        Employee employee0 = (Employee) PropertyUtils.getIndexedProperty(indexedBean, name0);
        System.out.println(employee0.getLastName());

        int index1 = 1;
        String name1 = "employeeList[" + index1 + "]";
        Employee employee1 = (Employee) PropertyUtils.getIndexedProperty(indexedBean, name1);
        System.out.println(employee1.getLastName());

        Employee employee00 = (Employee) PropertyUtils.getIndexedProperty(indexedBean, "employeeList", 0);
        Employee employee11 = (Employee) PropertyUtils.getIndexedProperty(indexedBean, "employeeList", 1);
        System.out.println(employee00.getLastName());
        System.out.println(employee11.getLastName());

        Integer i = (Integer) PropertyUtils.getIndexedProperty(indexedBean, "intArr", 1);
        System.out.println(i);
    }

}
