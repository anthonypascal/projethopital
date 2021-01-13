package com.company.users;

import com.company.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Patient extends User {

    private static final Map<String, Patient> patients = new HashMap<String, Patient>();

    private String secSoc;
    private String postalAdress;
    private String phoneNumber;
    private String mailAdress;

    public static Patient gets(String string, Patient patient) {
        if (!patients.containsKey(string)) {
            System.out.println("New patient added.");
            patients.put(string, patient);
        } else {
            System.out.println("User already exist");
        }
        return patients.get(string);
    }

    public Patient() {
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getSecSoc() {
        return secSoc;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalAdress() {
        return postalAdress;
    }

    public static Map<String, Patient> getPatients() {
        return patients;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPostalAdress(String postalAdress) {
        this.postalAdress = postalAdress;
    }

    public void setSecSoc(String secSoc) {
        this.secSoc = secSoc;
    }

    public static void createNewPatientMenu(String lastName, String firstName) {
        System.out.println(lastName + " " + firstName);

        Patient patient = new Patient();
        patient.setName(lastName);
        patient.setFirstName(firstName);

        Scanner scanner = Main.getScanner();

        try (scanner) {
            String menu = "===============\n" +
                    "Enter Mail Adress (UNIQUE) :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args[0].contains(".") && args[0].contains("@")){
                    boolean isNew = true;
                    for (Patient listedPatient : Patient.getPatients().values()) {
                        if (listedPatient.getMailAdress().equalsIgnoreCase(args[0])) {
                            System.out.println("Cette adresse mail est djà utilisée !");
                            isNew = false;
                            break;
                        }
                    }

                    if (isNew) {
                        patient.setMailAdress(args[0]);
                        break;
                    }
                }
            }

            menu = "===============\n" +
                    "Enter Physical Adress :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    patient.setPostalAdress(command);
                    break;
                }
            }

            menu = "===============\n" +
                    "Enter Phone Number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    patient.setPhoneNumber(command);
                    break;
                }
            }

            menu = "===============\n" +
                    "Enter Social Number :\n" +
                    "===============\n";
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0) {
                    patient.setSecSoc(command);
                    break;
                }
            }

            Patient.gets(patient.getMailAdress(), patient);
        }

    }
}