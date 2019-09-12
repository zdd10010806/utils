package com.zdd.beanUtils;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.zdd.beans.User;
import lombok.Data;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomConverters {

    /**
     *  map->bean 修改value的属性
     *
     * @param args
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        ConvertUtils.register(new StringConverter(), String.class);

        Map<String, String> map = new HashMap<>();
        map.put("name", "001");
        map.put("address", "hz");
        map.put("id", "100");
        map.put("state", "false");


        User u = new User();
        BeanUtils.populate(u, map);
        BeanMap beanMap = new BeanMap(u);

        System.out.println("====================");
        Set<Map.Entry<Object, Object>> entries = beanMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("====================");
        Map<String, Object> stringObjectMap = transBean2Map(u);

        System.out.println("====================");
        Set<Map.Entry<String, Object>> entries1 = stringObjectMap.entrySet();
        for (Map.Entry<String, Object> entry : entries1){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("====================");

        System.out.println(u);
    }

    /**
     * bean -> map ,修改key的格式
     *
     * @param obj
     * @return
     */


    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = Maps.newHashMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key), value);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }
}

class StringConverter implements Converter {
    /**
     *
     *
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
     * @param type
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<T> type, Object value)
    {

        if(String.class.isInstance(value)){
            return (T) ("###" + value);
        }else{
            return (T) value;
        }

    }
}

