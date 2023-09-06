package com.company;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private String name;
    private String surname;
    private Date dob;
    private String mobileNo;
    private String gender;

    public Person(String name, String surname, Date dob, String mobileNo, String gender) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.gender = gender;
    }

    public Person() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}