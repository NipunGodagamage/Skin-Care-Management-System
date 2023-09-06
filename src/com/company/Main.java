package com.company;

import com.company.gui.GUIConsultation;
import com.company.gui.GUIHome;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.LoadFromFile(WestminsterSkinConsultationManager.doctorList);

        System.out.println("\n                             *** Welcome to Westminster Skin Consultation Manager ***");

        while(true){
            System.out.println("************************************************************************************************************");
            System.out.println("|                                          MAIN MENU                                                       |");
            System.out.println("************************************************************************************************************");
            System.out.println("<   1   -                        Add new Doctor                                                            >");
            System.out.println("<   2   -                        Delete a Doctor                                                           >");
            System.out.println("<   3   -                        Print the list of the Doctor                                              >");
            System.out.println("<   4   -                        Save in file                                                              >");
            System.out.println("<   5   -                        Open GUI                                                                  >");
            System.out.println("<   6   -                        Quit                                                                      >");
            System.out.println("************************************************************************************************************");

            System.out.println("Enter your selection:");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "1":
                    manager.AddNewDoctor();
                    break;
                case "2" :
                    manager.DeleteDoctor();
                    break;
                case "3" :
                    manager.PrintListOfDoctor();
                    break;
                case "4" :
                    manager.SaveInFile();
                    break;
                case "5" :
                    EventQueue.invokeLater(() -> {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                UnsupportedLookAndFeelException e) {
                            System.out.println("Something went wrong with GUI");
                        }
                        new GUIHome();
                    });
                    break;
                case "6" :
                    System.out.println("\n       Thank you .......");
                    System.exit(1);

            }

        }

    }
}