package com.zdd.beanUtils;


import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class MapPropertyAccess {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        MappedBean employee = new MappedBean();
        Map<String, Object> map = new HashMap<>();
        //employee.setMapProperty(map);
        PropertyUtils.setSimpleProperty(employee, "mapProperty", map);

        PropertyUtils.setMappedProperty(employee, "mapProperty", "testKey1", "testValue1");
        PropertyUtils.setMappedProperty(employee, "mapProperty(testKey2)", "testValue2");
        Object mappedProperty = PropertyUtils.getMappedProperty(employee, "mapProperty", "testKey1");
        System.out.println(mappedProperty);

        System.out.println(employee.getMapProperty().get("testKey1"));
        System.out.println(employee.getMapProperty().get("testKey2"));

    }
}
