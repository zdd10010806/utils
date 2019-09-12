package com.zdd.beanUtils;


import java.util.Date;


public class Employee {
    private String firstName;
    private String lastName;
    private Date hireDate;
    private boolean isManager;


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Date getHireDate() {
        return hireDate;
    }


    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


    public boolean isManager() {
        return isManager;
    }


    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }


}
