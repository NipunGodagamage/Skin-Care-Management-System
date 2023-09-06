package com.company;

import java.util.Date;

public class Doctor extends Person implements Comparable<Doctor>{
    private String medicalLicenseNumber;
    private String specialization;

    public Doctor(){ }

    public Doctor(String name, String surName, Date dob, String mob, String gender, String medicalLicenseNumber, String specialization) {

        super(name, surName, dob, mob, gender);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }


    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void  setMedicalLicenseNumber (String  medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public int compareTo(Doctor o) {
        return this.getSurname().compareToIgnoreCase(o.getSurname());
    }
}
