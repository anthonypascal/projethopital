package com.company;

import com.company.rdv.Rdv;
import com.company.users.Doctor;
import com.company.users.Patient;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World");
        //try ( Scanner scanner = new Scanner( System.in ) ) {
        try {

            String menu = "===============\n" +
                    "Choose your command :\n" +
                    " > create patient <name> <firstName>: Creates a new patient\n" +
                    " > create doctor <name> <firstName>: Creates a new doctor\n" +
                    " > create rendezvous : Creates a new rendez-vous\n" +
                    " > exit : leave the program\n" +
                    "===============\n";

            while( true ) {
                System.out.println(menu);
                String command = scanner.nextLine();
                System.out.println(command);
                String[] commandArr = command.split(" ");

                if (command.startsWith("create patient") && commandArr.length > 3) {
                    Patient.createNewPatientMenu(commandArr[2], commandArr[3]);
                } else if (command.startsWith("create doctor") && commandArr.length > 3) {
                    Doctor.createNewDoctorMenu(commandArr[2], commandArr[3]);
                } else if (command.startsWith("create rendezvous") && commandArr.length > 1) {
                    Rdv.createNewDoctorMenu();
                } else if (command.startsWith("exit")) {
                    return;
                }
            }

        } catch (IllegalStateException e) {
            System.out.println("erreur main");
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
