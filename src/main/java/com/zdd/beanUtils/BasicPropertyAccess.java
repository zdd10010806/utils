package com.zdd.beanUtils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class BasicPropertyAccess {

    /**
     * @param args
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    public static void main(String[] args)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Employee employee = new Employee();
        String firstName = (String) PropertyUtils.getSimpleProperty(employee, "firstName");
        String lastName = (String) PropertyUtils.getSimpleProperty(employee, "lastName");

        firstName = firstName == null ? "Pi" : "";
        lastName = lastName == null ? "Chen" : "";

        PropertyUtils.setSimpleProperty(employee, "firstName", firstName);
        PropertyUtils.setSimpleProperty(employee, "lastName", lastName);

        System.out.println(employee.getFullName());
    }

}
