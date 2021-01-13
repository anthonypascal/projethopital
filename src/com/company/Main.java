package com.company;

import com.company.users.Patient;

import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
        try ( scanner ) {

            String menu = "===============\n" +
                    "Choose your command :\n" +
                    " > create patient <name> <firstName>: Creates a new patient\n" +
                    " > exit : leave the program\n" +
                    "===============\n";

            while( true ) {
                System.out.println(menu);
                String command = scanner.nextLine();
                System.out.println(command);
                String[] commandArr = command.split(" ");


                if (command.startsWith("create patient") && commandArr.length > 3) {
                    Patient.createNewPatientMenu(commandArr[2], commandArr[3]);
                } else if (command.startsWith("exit")) {
                    return;
                }
            }

        }
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
