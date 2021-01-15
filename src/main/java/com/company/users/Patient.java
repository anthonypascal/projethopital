package com.company.users;

import com.company.users.usersobjects.PatientObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient {

    private static final Map<Integer, PatientObject> patientsObject = new HashMap<Integer, PatientObject>();

    public static PatientObject gets(Integer secSoc, PatientObject patient) {
        if (!patientsObject.containsKey(secSoc)) {
            System.out.println("New patient added.");
            patientsObject.put(secSoc, patient);
        } else {
            System.out.println("User already exist");
        }
        return patientsObject.get(secSoc);
    }

    public static Map<Integer, PatientObject> getPatients() {
        return patientsObject;
    }

    private  static String mailAdressMenu() {
        Scanner scanner = new Scanner(System.in);
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        try {

            String menu = "===============\n" +
                    "Enter a Mail Adress :\n" +
                    "Format : ___@___.__\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");
                if (command.startsWith("exit")) {
                    return null;
                } else if (args.length == 1) {
                    Matcher matcher = pattern.matcher(args[0]);
                    if (matcher.matches()) {
                        return args[0];
                    } else {
                        System.out.println("The text isn't a possible mail adress.");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument secsoc menu");
        }
        return null;
    }

    private static int secSocMenu() {
        Scanner scanner = new Scanner(System.in);
        try {

            String menu = "===============\n" +
                    "Enter a Social Security Number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return -1;
                } else if (args.length > 0) {
                    try {
                        int matriculeConv = Integer.parseInt(args[0]);
                        if (!patientsObject.containsKey(matriculeConv)) {
                            return matriculeConv;
                        } else {
                            System.out.println("Ce numéro est déjà utilisé !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("mauvais format de texte");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument secsoc menu");
        }
        return -1;
    }

    private static String stringMenu(String what) {
        Scanner scanner = new Scanner(System.in);

        String instruction = "===============\n" +
                "Enter " + what + " :\n" +
                "===============\n";

        try {
            while (true) {
                System.out.println(instruction);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return null;
                } else if (args.length > 0) {
                    return command;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument string menu");
        }
        return null;
    }

    public static void createNewPatientMenu() {

        String lastName;
        String firstName;

        String mailAdress;
        String postalAdress;
        String phoneNumber;
        int secSoc;

        //try (scanner) {
        secSoc = secSocMenu();
        if (secSoc == - 1) {
            return;
        }
        lastName = stringMenu("last name");
        if (lastName == null) {
            return;
        }
        firstName = stringMenu("first name");
        if (firstName == null) {
            return;
        }
        mailAdress = mailAdressMenu();
        if (mailAdress == null) {
            return;
        }
        postalAdress = stringMenu("postal adress");
        if (postalAdress == null) {
            return;
        }
        phoneNumber = stringMenu("phone number (+XX)");
        if (phoneNumber == null) {
            return;
        }
        //Patient.gets(secSoc, new Patient(lastName, firstName, secSoc, postalAdress, phoneNumber, mailAdress));
        Patient.gets(secSoc, new PatientObject(secSoc, lastName, firstName, mailAdress, postalAdress, phoneNumber));
    }

    public static void display(String secSoc) {
        System.out.println("SEC SOC     | NOM        | PRENOM      | MAIL ADRESS   | POSTAL ADRESS     | PHONE NUMBER    | HOSPITALS     ");
        if (secSoc != null) {
            try {
                if (patientsObject.containsKey(Integer.parseInt(secSoc))) {
                    PatientObject patient = patientsObject.get(Integer.parseInt(secSoc));
                    System.out.println(patient.getSecSoc() + " | " + patient.getName() + " | " + patient.getFirstName() +
                            " | " + patient.getMailAdress() + " | " + patient.getPostalAddress() + " | " + patient.getPhoneNumber());
                } else {
                    System.out.println("Ce patient n'existe pas");
                }
            } catch (NumberFormatException e) {
                System.out.println("Mauvaise syntaxe sur l'affichage d'un patient");
            }
        } else {
            System.out.println("");
            for (PatientObject patient : getPatients().values()) {
                System.out.println(patient.getSecSoc() + " | " + patient.getName() + " | " + patient.getFirstName() +
                        " | " + patient.getMailAdress() + " | " + patient.getPostalAddress() + " | " + patient.getPhoneNumber());
            }
        }
    }

    public static void removePatient() {
        String menu = "===============\n" +
                "Enter the Patient Sec soc number to delete :\n" +
                "===============\n";
        Scanner scanner = new Scanner(System.in);
        try {

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    try {
                        int secSoc = Integer.parseInt(args[0]);
                        if (patientsObject.containsKey(secSoc)) {
                            patientsObject.remove(secSoc);
                            System.out.println("Deleted patient");
                            break;
                        } else {
                            System.out.println("The specified patient doesn't exist");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Delete patient NumberFormatExeption");
                    }

                }
            }

        } catch (IllegalStateException e){
            System.out.println("Error while creating appointment");
        }
    }

    public static void editPatient(String oldSecSoc) {
        PatientObject patient;
        try {
            int testSecSoc = Integer.parseInt(oldSecSoc);
            if (getPatients().containsKey(testSecSoc)) {
                patient = getPatients().get(testSecSoc);
            } else {
                System.out.println("Ce patient n'existe pas");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Soucis de format edit patient");
            return;
        }

        String menu = "===============\n" +
                "1 - secsoc : change patient sec soc number" +
                "2 - name : change patient name" +
                "3 - firstname : change patient first name\n" +
                "4 - mail : change patient mail address\n" +
                "5 - address : change patient postal address\n" +
                "" +
                "0 - exit : back to the previous menu\n" +
                "===============\n";
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("secsoc")) {
                    int secSoc = secSocMenu();
                    if (secSoc == -1) {
                        System.out.println("Annulation");
                        return;
                    } else {
                        patientsObject.remove(patient.getSecSoc());
                        patientsObject.put(secSoc, patient.setSecSoc(secSoc));
                        System.out.println("edited social security number");
                    }
                } else if (command.startsWith("2") || command.startsWith("name")) {
                    String lastName = stringMenu("last name");
                    if (lastName != null) {
                        patientsObject.put(patient.getSecSoc(), patient.setName(lastName));
                        System.out.println("edited last name");
                    } else {
                        System.out.println("Annulation");
                        return;
                    }
                } else if (command.startsWith("3") || command.startsWith("firstname")) {
                    String firstName = stringMenu("first name");
                    if (firstName != null) {
                        patientsObject.put(patient.getSecSoc(), patient.setFirstName(firstName));
                        System.out.println("edited first name");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("4") || command.startsWith("mail")) {
                    String mailAddress = mailAdressMenu();
                    if (mailAddress != null) {
                        patientsObject.put(patient.getSecSoc(), patient.setMailAdress(mailAddress));
                        System.out.println("edited mail address");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("5") || command.startsWith("address")) {
                    String address = stringMenu("postal address");
                    if (address != null) {
                        patientsObject.put(patient.getSecSoc(), patient.setPostalAddress(address));
                        System.out.println("edited postal address");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("6") || command.startsWith("phone")) {
                    String phone = stringMenu("phone number");
                    if (phone != null) {
                        patientsObject.put(patient.getSecSoc(), patient.setPhoneNumber(phone));
                        System.out.println("Edited phone number");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument edit patient menu");
        }
    }

}
