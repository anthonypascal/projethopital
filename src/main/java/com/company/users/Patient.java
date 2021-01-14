package com.company.users;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Patient extends User {

    private static final Map<Integer, Patient> patients = new HashMap<Integer, Patient>();

    private int secSoc;
    private String postalAdress;
    private String phoneNumber;
    private String mailAdress;

    public static Patient gets(Integer secSoc, Patient patient) {
        if (!patients.containsKey(secSoc)) {
            System.out.println("New patient added.");
            patients.put(secSoc, patient);
        } else {
            System.out.println("User already exist");
        }
        return patients.get(secSoc);
    }

    public Patient(String lastName, String firstName, int secSoc, String postalAdress, String phoneNumber, String mailAdress) {
        setName(lastName);
        setFirstName(firstName);
        this.secSoc = secSoc;
        this.postalAdress = postalAdress;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    public int getSecSoc() {
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

    public static Map<Integer, Patient> getPatients() {
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

    public void setSecSoc(int secSoc) {
        this.secSoc = secSoc;
    }

    public static void createNewPatientMenu(String lastName, String firstName) {

        Scanner scanner = new Scanner(System.in);

        String mailAdress;
        String postalAdress;
        String phoneNumber;
        int secSoc;

        //try (scanner) {
        try {
            String menu = "===============\n" +
                    "Enter Social Number :\n" +
                    "===============\n";
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0) {
                    try {
                        int secSocConv = Integer.parseInt(args[0]);
                        if (!Patient.patients.containsKey(secSocConv)) {
                            secSoc = secSocConv;
                            break;
                        } else {
                            System.out.println("Ce numéro de sécurité sociale est déjà utilisé !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("mauvais format de texte");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter Mail Adress (UNIQUE) :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args[0].contains(".") && args[0].contains("@")){
                    mailAdress = args[0];
                    break;
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
                    postalAdress = command;
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
                    phoneNumber = command;
                    break;
                }
            }

            Patient patient = Patient.gets(secSoc, new Patient(lastName, firstName, secSoc, postalAdress, phoneNumber, mailAdress));
            System.out.println(patient.getName() + " " + patient.getFirstName());
        } catch (IllegalStateException e){
            System.out.println("erreur création Patient");
        }

    }
}
