package com.company.gui;

import com.company.Consultation;
import com.company.Doctor;
import com.company.Patient;
import com.company.WestminsterSkinConsultationManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class GUIConsultation  extends JFrame {
    public static ArrayList<Patient> patientList = new ArrayList<>();
    public static ArrayList<Consultation> consultationList = new ArrayList<>();
    private JTextField nameTxt, surnameTxt,mobileTxt;
    private JLabel dobLbl;
    private JTextArea notesTxt;
    private JRadioButton male;
    private JButton backBtn;
    private Date dob,pickedDate;
    private String time;
    private JComboBox  docCombo, timeCombo;

    public static void main(String[] args) {
        SaveInFile();
    }

    public GUIConsultation() {
        this.setTitle("Westminster Skin Consultation Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(560, 700);
        this.setResizable(false);
        this.setLayout(null);

        LoadFromFile();

        backBtn = new JButton("❮ Back");
        backBtn.setBounds(420, 10, 100, 40);

        JLabel headingLbl = new JLabel("Book a Consultation");
        headingLbl.setBounds(100, 10, 300, 40);
        headingLbl.setFont(new Font("", Font.BOLD, 30));


        JLabel patientDetailsLbl = new JLabel("Patient Details");
        patientDetailsLbl.setFont(new Font("", Font.BOLD, 25));
        patientDetailsLbl.setBounds(50, 60, 300, 50);

        JLabel nameLbl = new JLabel("First Name");
        nameLbl.setBounds(50, 120, 100, 30);
        nameLbl.setFont(new Font("", Font.BOLD, 14));

        nameTxt = new JTextField();
        nameTxt.setBounds(50, 150, 200, 30);
        nameTxt.setFont(new Font("", Font.BOLD, 14));

        JLabel surnameLbl = new JLabel("Surname");
        surnameLbl.setBounds(300, 120, 100, 30);
        surnameLbl.setFont(new Font("", Font.BOLD, 14));

        surnameTxt = new JTextField();
        surnameTxt.setBounds(300, 150, 200, 30);
        surnameTxt.setFont(new Font("", Font.BOLD, 14));

        dobLbl = new JLabel("Date Of Birth ");
        dobLbl.setBounds(50, 190, 150, 30);
        dobLbl.setFont(new Font("", Font.BOLD, 14));

        JDatePickerImpl dobPicker = datePicker();
        dobPicker.setBounds(50,220,200,30);

        JLabel mobileLbl = new JLabel("Mobile Number");
        mobileLbl.setBounds(300, 190, 150, 30);
        mobileLbl.setFont(new Font("", Font.BOLD, 14));

        mobileTxt = new JTextField();
        mobileTxt.setBounds(300, 220, 200, 30);
        mobileTxt.setFont(new Font("", Font.BOLD, 14));

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("", Font.BOLD, 14));
        genderLbl.setBounds(50,270,150,30);

        male = new JRadioButton("Male");
        male.setFont(new Font("", Font.PLAIN, 14));
        male.setSelected(true);
        male.setBounds(200,270,100,30);

        JRadioButton female = new JRadioButton("Female");
        female.setFont(new Font("", Font.PLAIN, 14));
        female.setSelected(false);
        female.setBounds(300,270,100,30);

        //gender button group
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JSeparator horizontalLine = new JSeparator();
        horizontalLine.setOrientation(SwingConstants.HORIZONTAL);
        horizontalLine.setBounds(20, 320, 500, 10);

        //add consultation form

        JLabel addConsultationLbl = new JLabel("Add Consultation");
        addConsultationLbl.setFont(new Font("", Font.BOLD, 25));
        addConsultationLbl.setBounds(50, 340, 300, 50);

        JLabel selectDocLbl = new JLabel("Select a Doctor");
        selectDocLbl.setBounds(50, 390, 150, 30);
        selectDocLbl.setFont(new Font("", Font.BOLD, 14));

        String[] doctorAr = new String[WestminsterSkinConsultationManager.doctorList.size()];
        int index = 0;
        for(Doctor d: WestminsterSkinConsultationManager.doctorList){
            doctorAr[index] = d.getName() + " " + d.getSurname();
            index++;
        }

        docCombo = new JComboBox(doctorAr);
        docCombo.setBounds(300, 390, 200, 30);
        docCombo.setFont(new Font("", Font.PLAIN, 14));

        JLabel dateLbl = new JLabel("Pick a Date");
        dateLbl.setBounds(50, 430, 150, 30);
        dateLbl.setFont(new Font("", Font.BOLD, 14));

        JDatePickerImpl cDate = datePicker();
        cDate.setBounds(300,430,200,30);

        JLabel timeSlotLbl = new JLabel("Time Slot");
        timeSlotLbl.setBounds(50, 470, 150, 30);
        timeSlotLbl.setFont(new Font("", Font.BOLD, 14));

        String[] timeSlots = {"8 a.m. - 9 a.m.","9 a.m. - 10 a.m.","10 a.m. - 11 a.m.",
                "11 a.m. - 12 a.m.","2 p.m. - 3 p.m.", "3 p.m. - 4 p.m.","4 p.m. - 5 p.m.", "5 p.m. - 6 p.m."};

        timeCombo = new JComboBox(timeSlots);
        timeCombo.setBounds(300, 470, 200, 30);
        timeCombo.setFont(new Font("", Font.PLAIN, 14));

        JLabel notesLbl = new JLabel("Add Notes");
        notesLbl.setBounds(50, 510, 150, 30);
        notesLbl.setFont(new Font("", Font.BOLD, 14));

        notesTxt = new JTextArea();
        notesTxt.setLineWrap(true);
        notesTxt.setFont(new Font("", Font.PLAIN, 14));

        JScrollPane textAreaScroll = new JScrollPane(notesTxt);
        textAreaScroll.setBounds(300, 510, 200, 75);

        JButton bookBtn = new JButton("Book Now");
        bookBtn.setBounds(50, 610, 200, 30);
        bookBtn.setFont(new Font("", Font.BOLD, 14));

        JButton viewBtn = new JButton("View Consultations");
        viewBtn.setBounds(300, 610, 200, 30);
        viewBtn.setFont(new Font("", Font.BOLD, 14));






        this.add(backBtn);
        this.add(headingLbl);
        this.add(patientDetailsLbl);
        this.add(nameLbl);
        this.add(nameTxt);
        this.add(surnameLbl);
        this.add(surnameTxt);
        this.add(dobLbl);
        this.add(dobPicker);
        this.add(mobileLbl);
        this.add(mobileTxt);
        this.add(genderLbl);
        this.add(male);
        this.add(female);
        this.add(horizontalLine);
        this.add(addConsultationLbl);
        this.add(selectDocLbl);
        this.add(docCombo);
        this.add(dateLbl);
        this.add(cDate);
        this.add(timeSlotLbl);
        this.add(timeCombo);
        this.add(notesLbl);
        this.add(textAreaScroll);
        this.add(bookBtn);
        this.add(viewBtn);

        //action Listeners
        backBtn.addActionListener(e -> {
            if (e.getSource() == backBtn) {
                this.dispose();
                new GUIHome();
            }
        });

        viewBtn.addActionListener(e -> {
            if (e.getSource() == viewBtn) {
                this.dispose();
                new GUIConsultationTableView();
            }
        });

        bookBtn.addActionListener(e -> {
            if (e.getSource() == bookBtn) {
                if (!nameTxt.getText().isEmpty() && !surnameTxt.getText().isEmpty() && !mobileTxt.getText().isEmpty()) {
                    String name = nameTxt.getText();
                    String surname = surnameTxt.getText();
                    dob =(Date) dobPicker.getModel().getValue();
                    String mobile = mobileTxt.getText();
                    String gender;
                    if (male.isSelected()) {
                        gender = "Male";
                    } else {
                        gender = "Female";
                    }

                    pickedDate = (Date) cDate.getModel().getValue();

                    time = (String) timeCombo.getSelectedItem();
                    String notes = notesTxt.getText();
                    Doctor doctor = WestminsterSkinConsultationManager.doctorList.get(docCombo.getSelectedIndex());

                    if(!isAvailable(doctor)){
                        for(Doctor d:WestminsterSkinConsultationManager.doctorList){
                            if(isAvailable(d)){
                                JOptionPane.showMessageDialog(this, "Doctor not available, Random doctor will be assign", "ALERT!", JOptionPane.INFORMATION_MESSAGE);
                                doctor = d;

                            }
                        }
                    }

                    boolean flag = false;

                    Patient tempPatient = new Patient();
                    for (Patient patient : patientList) {
                        if (name.equalsIgnoreCase(patient.getName()) && surname.equalsIgnoreCase(patient.getSurname())) {
                            tempPatient = patient;
                            flag = true;
                            break;
                        }
                    }
                    Consultation consultation = new Consultation();
                    consultation.setTimeSlots(time);
                    consultation.setDate(pickedDate);
                    consultation.setDoctor(doctor);
                    consultation.setPatient(tempPatient);
                    consultation.setNotes(notes);
                    String cost ="";
                    if (flag) {
                        cost = "£25";
                    } else {
                        cost = "£15";
                        tempPatient = new Patient(name, surname, dob, mobile, gender);
                        consultation.setPatient(tempPatient);
                        patientList.add(tempPatient);
                    }
                    consultation.setCost(cost);
                    consultationList.add(consultation);
                    JOptionPane.showMessageDialog(this, "Your consultation reserved Successfully!\nYour Cost will be "+cost, "ALERT!", JOptionPane.INFORMATION_MESSAGE);

                    SaveInFile();
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill all the fields.", "ALERT!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        this.setVisible(true);
    }

    public static void SaveInFile() {
        try {
            FileOutputStream fo = new FileOutputStream("PatientList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fo);

            for (Patient patient : patientList) {
                oos.writeObject(patient);
            }
            fo.close();
            oos.close();


            FileOutputStream fo1 = new FileOutputStream("Consultation.txt");
            ObjectOutputStream oos1 = new ObjectOutputStream(fo1);

            for (Consultation consultation : consultationList) {
                oos1.writeObject(consultation);
            }
            fo1.close();
            oos1.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void LoadFromFile() {
        try {
            FileInputStream fis1 = new FileInputStream("Consultation.txt");
            FileInputStream fis = new FileInputStream("PatientList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

//            FileInputStream fis1 = new FileInputStream("Consultation.txt");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            patientList = new ArrayList<>();
            consultationList = new ArrayList<>();
            while (true) {
                try {
                    Patient patient = (Patient) ois.readObject();
                    Consultation consultation = (Consultation) ois1.readObject();
                    patientList.add(patient);
                    consultationList.add(consultation);
                } catch (Exception e) {
                    break;
                }
            }
            fis1.close();
            ois1.close();
            fis.close();
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAvailable(Doctor doctor){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for(Consultation c : consultationList){
            if(sdf.format(c.getDate()).equals(sdf.format(pickedDate)) && c.getTimeSlots().equals(time) && c.getDoctor().getMedicalLicenseNumber().equals(doctor.getMedicalLicenseNumber())){
                return false;
            }
        }
        return true;
    }

    private JDatePickerImpl datePicker(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //date picker for select date
        UtilDateModel dateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.day","Day");
        properties.put("text.month","Month");
        properties.put("text.year","Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel,properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {

            @Override
            public Object stringToValue(String text) throws ParseException {
                return (Object) sdf.parse(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                Calendar date = (Calendar) value;
                Date currDate = new Date();
                if(date == null){
                    return sdf.format(currDate);
                }
                return sdf.format(date.getTime());
            }
        });
        return datePicker;
    }
}