package com.company.gui;

import javax.swing.*;
import java.awt.*;

public class GUIHome extends JFrame {
    private JButton doctorInfoBtn,bookConsultationBtn;

    public GUIHome(){
        this.setTitle("Westminster Skin Consultation Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setResizable(false);
        this.setLayout(null);

        //set background image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/Pictures/background.jpg")));
        background.setBounds(0,0,800,500);
      

    
        //set heading
        JLabel welcomeLetter = new JLabel("Westminster Skin Consultation Manager");
        welcomeLetter.setBounds(50, 25, 600,50);
        welcomeLetter.setForeground(Color.BLACK);
        welcomeLetter.setFont(new Font("Arial", Font.BOLD,30));
        background.add(welcomeLetter);

        //doctor info button
        doctorInfoBtn = new JButton("Doctor Information");
        doctorInfoBtn.setBounds(50,150,400,80);
        doctorInfoBtn.setFont(new Font("Comic Sans", Font.BOLD,25));
        background.add(doctorInfoBtn);

        //book consultation button
        bookConsultationBtn = new JButton("Book a Consultation");
        bookConsultationBtn.setBounds(50,250,400,80);
        bookConsultationBtn.setFont(new Font("Comic Sans", Font.BOLD,25));


        //add action listeners
        doctorInfoBtn.addActionListener(e -> {
            if (e.getSource() == doctorInfoBtn){
                dispose();
                new GUIDoctorTableView();
            }
        });

        bookConsultationBtn.addActionListener(e -> {
            dispose();
            new GUIConsultation();
        });
        background.add(bookConsultationBtn);

        this.setVisible(true);
        this.add(background);
    }
}