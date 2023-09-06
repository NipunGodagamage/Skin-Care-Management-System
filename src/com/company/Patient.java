package com.company;
import java.util.Date;
import java.util.UUID;

public class Patient extends Person{

    private int id;
    private static int count = 0;


    public Patient(){};
    public Patient(String name, String surname, Date dob, String mobileNumber, String gender){
        super(name, surname, dob, mobileNumber, gender);
        this.id = count;
        count ++;
    }

    public int getId() {
        return this.id;
    }
    public void setID(int id){
        this.id = id;
    }

}