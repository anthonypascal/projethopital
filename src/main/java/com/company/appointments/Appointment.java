package com.company.appointments;

import com.company.users.Doctor;
import com.company.users.Patient;
import com.company.users.usersobjects.DoctorObject;
import com.company.users.usersobjects.PatientObject;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Appointment {

    private static final Map<String, AppointmentObject> appointmentsObject = new HashMap<>();

    public static AppointmentObject gets(String name, AppointmentObject appointment) {
        if (!appointmentsObject.containsKey(name)) {
            System.out.println("New rdv added.");
            appointmentsObject.put(name, appointment);
        } else {
            System.out.println("Rdv already exist");
        }
        return appointmentsObject.get(name);
    }

    public static Map<String, AppointmentObject> getAppointments() {
        return appointmentsObject;
    }

    private static Date dateMenu() {
        String menu = "===============\n" +
                "Enter Date :\n" +
                "Format : dd/MM/yyyy HH:mm\n" +
                "===============\n";

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return null;
                } else if (args.length == 2) {
                    try {
                        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(command);
                    } catch (ParseException parseException) {
                        System.out.println("Mauvais format de date");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur dans le dateMenu");
        }
        return null;
    }

    private static int intMenu(String what) {
        Scanner scanner = new Scanner(System.in);
        try {

            String menu = "===============\n" +
                    "Enter a " + what + " Number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return -1;
                } else if (args.length > 0) {
                    try {
                        return Integer.parseInt(args[0]);
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

    private static int userID(Class user) {
        int userID;
        if (user == Patient.class) {
            while (true) {
                userID = intMenu("patient Social Security number");
                if (userID == -1) {
                    return -1;
                } else if (Patient.getPatients().containsKey(userID)) {
                    return userID;
                } else {
                    System.out.println("Le numéro de sécurité sociale n'est pas correct. Réessayez");
                }
            }
        } else if (user == Doctor.class) {
            while (true) {
                userID = intMenu("doctor matricule");
                if (userID == -1) {
                    return -1;
                } else if (Doctor.getDoctors().containsKey(userID)) {
                    return userID;
                } else {
                    System.out.println("Le matricule n'est pas correct. Réessayez");
                }
            }
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

    public static void createNewAppointmentMenu() {
        int matricule;
        int secSoc;
        Date date;
        String name;
        String place;

        while (true) {
            name = stringMenu("appointment name");
            if (name == null) {
                return;
            } else if (!appointmentsObject.containsKey(name)) {
                break;
            } else {
                System.out.println("ce nom de rendez-vous existe déjà");
            }
        }

        secSoc = userID(Patient.class);
        if (secSoc == -1) {
            return;
        }

        matricule = userID(Doctor.class);
        if (matricule == -1) {
            return;
        }

        place = stringMenu("place");
        if (place == null) {
            return;
        }

        date = dateMenu();
        if (date == null) {
            return;
        }

        Appointment.gets(name, new AppointmentObject(name, place, date, secSoc, matricule));
        appointmentsObject.put(name, new AppointmentObject(name, place, date, secSoc, matricule));
    }

    public static void removeAppointment() {

        Scanner scanner = new Scanner(System.in);
        try {
            String menu = "===============\n" +
                    "Enter the Appointment name to delete :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    if (appointmentsObject.containsKey(command)) {
                        appointmentsObject.remove(command);
                        System.out.println("Deleted appointment");
                        break;
                    } else {
                        System.out.println("The specified appointment doesn't exist");
                    }

                }
            }

        } catch (IllegalStateException e){
            System.out.println("Error while creating appointment");
        }
    }

    public static void display(String matricule) {
        System.out.println("NAME     | PLACE        | DATE      | SEC SOC   | MATRICULE   |");
        if (matricule != null) {
            if (appointmentsObject.containsKey(matricule)) {
                AppointmentObject appointment = appointmentsObject.get(matricule);
                System.out.println(appointment.getName() + " | " + appointment.getPlace() +
                        " | " + appointment.getDate() + " | " + appointment.getSecSoc() +
                        " | " + appointment.getMatricule());
            } else {
                System.out.println("Ce rendez-vous n'existe pas");
            }
        } else {
            System.out.println("");
            for (AppointmentObject appointment : appointmentsObject.values()) {
                System.out.println(appointment.getName() + " | " + appointment.getPlace() +
                        " | " + appointment.getDate() + " | " + appointment.getSecSoc() +
                        " | " + appointment.getMatricule());
            }
        }
    }

    private static int matriculeMenu() {
        Scanner scanner = new Scanner(System.in);
        try {

            String menu = "===============\n" +
                    "Enter an identification number :\n" +
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
                        if (Doctor.getDoctors().containsKey(matriculeConv)) {
                            return matriculeConv;
                        } else {
                            System.out.println("Ce médecin n'existe pas !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("mauvais format de texte");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument matricule menu");
        }
        return -1;
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
                        int secSocConv = Integer.parseInt(args[0]);
                        if (Patient.getPatients().containsKey(secSocConv)) {
                            return secSocConv;
                        } else {
                            System.out.println("Ce patient n'existe pas !");
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

    public static void editAppointment(String name) {
        AppointmentObject appointment;
        if (getAppointments().containsKey(name)) {
            appointment = getAppointments().get(name);
        } else {
            System.out.println("Ce appointment n'existe pas");
            return;
        }

        String menu = "===============\n" +
                "1 - name : change appointment name\n" +
                "2 - place : change appointment place\n" +
                "3 - date : change appointment date\n" +
                "4 - secsoc : change appointment social security number\n" +
                "5 - matricule : change appointment matricule\n" +
                "" +
                "0 - exit : back to the previous menu\n" +
                "===============\n";
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("name")) {
                    String newName = stringMenu("name");
                    if (newName != null) {
                        appointmentsObject.remove(name);
                        appointmentsObject.put(newName, appointment.setName(newName));
                        System.out.println("edited name");
                    } else {
                        System.out.println("Annulation");
                        return;
                    }
                } else if (command.startsWith("2") || command.startsWith("place")) {
                    String place = stringMenu("place");
                    if (place != null) {
                        appointmentsObject.replace(appointment.getName(), appointment.setPlace(place));
                        System.out.println("edited place");
                    } else {
                        System.out.println("Annulation");
                        return;
                    }
                } else if (command.startsWith("3") || command.startsWith("date")) {
                    Date date = dateMenu();
                    if (date != null) {
                        appointmentsObject.replace(appointment.getName(), appointment.setDate(date));
                        System.out.println("edited date");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("4") || command.startsWith("secsoc")) {
                    int secSoc = secSocMenu();
                    if (secSoc != -1) {
                        appointmentsObject.replace(appointment.getName(), appointment.setSecSoc(secSoc));
                        System.out.println("edited sec soc");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("5") || command.startsWith("matricule")) {
                    int matricule = matriculeMenu();
                    if (matricule != -1) {
                        appointmentsObject.replace(appointment.getName(), appointment.setMatricule(matricule));
                        System.out.println("edited matricule");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument edit appointment menu");
        }
    }
}
