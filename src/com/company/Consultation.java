package com.company;

import java.util.Date;

    public class Consultation extends Doctor{
        private String  timeSlots;
        private String cost;
        private String notes;
        private Date date;
        private Doctor doctor;
        private Patient patient;

        public Consultation(String timeSlots, String cost, String notes, Date date, Doctor doctor, Patient patient) {
            this.timeSlots = timeSlots;
            this.cost = cost;
            this.notes = notes;
            this.date = date;
            this.doctor = doctor;
            this.patient = patient;
        }

        public Consultation() {
        }

        public String getTimeSlots() {
            return timeSlots;
        }

        public void setTimeSlots(String timeSlots) {
            this.timeSlots = timeSlots;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }
    }
