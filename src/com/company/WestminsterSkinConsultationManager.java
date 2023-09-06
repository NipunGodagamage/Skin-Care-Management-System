package com.company;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements  SkinConsultationManager {
    public static ArrayList<Doctor> doctorList = new ArrayList<>(10);
    public static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final Scanner scanner = new Scanner(System.in);


    @Override
    public void AddNewDoctor() {
        String choice = "Y";
        while (choice.equalsIgnoreCase("Y")){
            try{
                int counter = doctorList.size();

                //validate only 10 doctors can register
                if ((counter != 10)){
                    System.out.println("==============================================================================");
                    System.out.println("|                                Add new doctor                              |");
                    System.out.println("==============================================================================");
                    System.out.println("Please enter the name of the doctor:");
                    String doctorName = scanner.nextLine();


                    System.out.println("Please enter the surname of the doctor:");
                    String surname = scanner.nextLine();

                    System.out.println("Please enter the mobile number of the doctor:");
                    String mobile = scanner.nextLine();

                    System.out.println("Please select the gender:");
                    System.out.println("1. Male\n2. Female");
                    int selectGender = Integer.parseInt(scanner.nextLine());
                    String gender;
                    if (selectGender == 1) {
                        gender = "Male";
                    } else if (selectGender == 2) {
                        gender = "Female";
                    }else {
                        System.out.println("You entered the wrong selection");
                        break;
                    }

                    System.out.println("Please enter the doctor's Date of Birth(dd/MM/yyyy):");
                    String doctorDob = scanner.nextLine();
                    Date dob = formatter.parse(doctorDob);


                    System.out.println("Please enter the Medical License Number of the doctor:");
                    String licenseNo = scanner.nextLine();

                    System.out.println("Please select the specialization from the below:");
                    System.out.println("1. cosmetic dermatology\n2. pediatric dermatology\n3. medical dermatology");
                    int number = scanner.nextInt();
                    String specialization = "";
                    if (number == 1){
                        specialization = "cosmetic dermatology";
                    } else if (number == 2) {
                        specialization = "pediatric dermatology";

                    } else if (number == 3) {
                        specialization = "medical dermatology";
                    }else {
                        System.out.println("You entered the wrong selection");
                    }

                    Doctor doctor = new Doctor(doctorName,surname, dob ,mobile,gender,licenseNo,specialization);
                    doctorList.add(doctor);

                    System.out.println("Doctor added successfully!");

                    System.out.println("Do you want add another doctor?(Y/N):");
                    choice = scanner.nextLine();
                }
                else {
                    System.out.println("Doctors has reached the maximum number. Please try again later");
                    break;
                }

            }catch (ParseException e){
                System.out.println("Invalid Date format!");
            }
            catch (Exception a){
                System.out.println("Doctor can't add, something went wrong!");
            }

        }

        System.out.println("Back to the main menu...");

    }

    @Override
    public void DeleteDoctor() {
        try{
            System.out.println("==============================================================================");
            System.out.println("|                                Delete a doctor                             |");
            System.out.println("==============================================================================");

            System.out.println("Please enter the medical license number:");
            String lLicenceNo = scanner.nextLine();

            if (!(doctorList.isEmpty())){
                for (Doctor doctor:doctorList) {
                    if (lLicenceNo.equals(doctor.getMedicalLicenseNumber())) {
                        System.out.println("Do you want to delete doctor " + doctor.getName() + "?(Y/N)");
                        String respond = scanner.nextLine();

                        if (respond.equalsIgnoreCase("Y")) {
                            doctorList.remove(doctor);
                            System.out.println("Doctor deleted successfully!");
                        } else {
                            System.out.println("Back to the main menu...");
                        }
                        break;
                    }
                }
            }else {
                System.out.println("Doctor not found!");
            }
        }catch (Exception e){
            System.out.println("Doctor can't delete, Something went wrong!");
        }

    }

    @Override
    public void PrintListOfDoctor() {
        try{
            System.out.println("==============================================================================");
            System.out.println("|                                List of doctors                             |");
            System.out.println("==============================================================================");

            if (doctorList.isEmpty()){
                System.out.println("\n");
                System.out.println("       Can't find any doctor to display. Try add new doctor first");
                System.out.println("\n");
            }
            else {
                //for store sorted list
                ArrayList<Doctor> sortedArray = new ArrayList<>(doctorList.size());

                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %s\n","Medical License Number", "Name", "Surname", "Gender", "Date of birth", "Mobile number",  "Specialization\n");

                //add main array list object to new arraylist
                sortedArray.addAll(doctorList);
                //sort new array list
                Collections.sort(sortedArray);
                for (Doctor doctor: sortedArray) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %s\n",doctor.getMedicalLicenseNumber(), doctor.getName(), doctor.getSurname(),
                            doctor.getGender(), formatter.format(doctor.getDob()), doctor.getMobileNo(),
                             doctor.getSpecialization());
                }



          
            }
            System.out.println("\n -------------------------------------------------------------------------------");
        }
        catch (Exception e){
            System.out.println("Can't print the list of the doctors, Something went wrong please try again later!");
        }
    }

    @Override
    public void SaveInFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("doctors.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Doctor doctor: doctorList) {
                objectOutputStream.writeObject(doctor);
            }
            fileOutputStream.close();
            objectOutputStream.close();
            System.out.println("Successfully saved to file!");

        } catch (Exception e) {
            System.out.println("Something went wrong please try again...");
        }
    }

    @Override
    public void LoadFromFile(ArrayList<Doctor> list){
        try{
            FileInputStream fileInputStream = new FileInputStream("doctors.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try{
                    Doctor doctor = (Doctor) objectInputStream.readObject();
                    list.add(doctor);
                }
                catch (Exception e){
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
        }
        catch (IOException e){
            System.out.println("File not found please try again...");
        }
    }
}