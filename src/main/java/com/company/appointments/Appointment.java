package com.company.appointments;

import com.company.users.Doctor;
import com.company.users.Patient;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Appointment {

    private static final Map<String, Appointment> appointments = new HashMap<>();

    private String name;
    private String place;
    private Date date;
    private int secSoc;
    private int matricule;


    public static Appointment gets(String name, Appointment appointment) {
        if (!appointments.containsKey(name)) {
            System.out.println("New rdv added.");
            appointments.put(name, appointment);
        } else {
            System.out.println("Rdv already exist");
        }
        return appointments.get(name);
    }

    public Appointment(String name, String place, Date date, int secSoc, int matricule) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.secSoc = secSoc;
        this.matricule = matricule;
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
            } else if (!appointments.containsKey(name)) {
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

        Appointment.gets(name, new Appointment(name, place, date, secSoc, matricule));
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
                    if (appointments.containsKey(command)) {
                        appointments.remove(command);
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

    public int getSecSoc() {
        return secSoc;
    }

    public int getMatricule() {
        return matricule;
    }

    public Date getDate() {
        return date;
    }

    public static Map<String, Appointment> getAppointments() {
        return appointments;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

}
