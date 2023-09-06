package com.company.gui;

import com.company.Consultation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class GUIConsultationTableView extends JFrame {
    private ArrayList<Consultation> consList = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private DefaultTableModel model;

    public GUIConsultationTableView(){
        this.setTitle("Westminster Skin Consultation Manager");
        this.setSize(1100, 683);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


        JLabel headingLbl = new JLabel("View Consultations");
        headingLbl.setFont(new Font("",Font.BOLD,24));
        headingLbl.setBounds(400,20,300,50);

        JButton backBtn = new JButton("❮ Back");
        backBtn.setBounds(900, 20, 100, 40);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setBounds(950, 500, 100, 40);
        
        //set table
        String[] consultationHeadings = {"Date","Time", "Doctor", "Patient","Notes","Cost"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(consultationHeadings);

        insertToConsTable();

        JTable consultationTable = new JTable(model);
        JScrollPane tablePane = new JScrollPane(consultationTable);
        tablePane.setBounds(50,100,1000,400);


        this.add(tablePane);
        this.add(headingLbl);
        this.add(backBtn);
        this.add(removeBtn);
        this.setVisible(true);

        //Action Listeners
        backBtn.addActionListener(e -> {
            if (e.getSource() == backBtn) {
                this.dispose();
                new GUIConsultation();
            }
        });

        removeBtn.addActionListener(e ->{
            if(e.getSource() == removeBtn){
                int index = consultationTable.getSelectedRow();
                if(index == -1){
                    JOptionPane.showMessageDialog(null,"Please Select a consultation to remove","ALERT!",JOptionPane.WARNING_MESSAGE);
                }else {
                    consList.remove(index);
                    model.setRowCount(0);
                    GUIConsultation.SaveInFile();
                    insertToConsTable();
                }
            }
        });
    }
    public void insertToConsTable(){
        consList = GUIConsultation.consultationList;
        for(Consultation c: consList){
            String date = sdf.format(c.getDate());
            String time = c.getTimeSlots();
            String docName = "Dr. "+ c.getDoctor().getName()+" "+ c.getDoctor().getSurname();
            String patient = c.getPatient().getName()+" " +c.getPatient().getSurname();
            model.addRow(new Object[] {date,time,docName,patient,c.getNotes(),c.getCost()});
        }
    }


}