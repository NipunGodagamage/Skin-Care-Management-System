package com.company.gui;

import com.company.Doctor;
import com.company.WestminsterSkinConsultationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class GUIDoctorTableView extends JFrame {
    private JButton backBtn;
    private JPanel topPanel;

    private JScrollPane scrollPane;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    GUIDoctorTableView() {
        this.setTitle("Doctor Information");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setLayout(null);
        this.setResizable(false);

        //top panel
        topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 1000, 100);

        //back button
        backBtn = new JButton("❮ Back");
        backBtn.setBounds(880, 20, 80, 50);


        JLabel label = new JLabel("Doctor Information");
        label.setBounds(300,20, 300, 50);

        label.setFont(new Font("Arial", Font.BOLD, 30));

        topPanel.add(label);
        topPanel.add(backBtn);

        //table
        final String[] columnName = {"Medical Number", "Name","Surname", "Date Of Birth", "Gender", "Mobile Number", "Specialization"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);

        //add values to table
        ArrayList<Doctor> tempAr = new ArrayList<>();
        tempAr.addAll(WestminsterSkinConsultationManager.doctorList);
        Collections.sort(tempAr);
        for(Doctor d: tempAr){
            model.addRow(new String[]{
                d.getMedicalLicenseNumber(),
                    d.getName(),
                    d.getSurname(),
                    sdf.format(d.getDob()),
                    d.getGender(),
                    d.getMobileNo(),
                    d.getSpecialization()
            });
        }

        //Defining the table
        JTable table = new JTable(model);
        table.setFont(new Font("Calibri", Font.PLAIN,13));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD,14));
        table.getTableHeader().setPreferredSize(new Dimension(90,40));
        table.setAutoCreateRowSorter(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 900,450);

        this.add(topPanel);
        this.add(scrollPane);
        this.setVisible(true);

        //action listeners
        backBtn.addActionListener(e -> {
            if (e.getSource() == backBtn) {
                this.dispose();
                new GUIHome();
            }
        });

    }
}