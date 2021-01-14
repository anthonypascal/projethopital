package com.company.reservation;

import com.company.rdv.Rdv;
import com.company.rooms.Room;
import com.company.users.Doctor;
import com.company.users.Patient;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reservation {
    private static final Map<Integer, Reservation> reservations = new HashMap<>();
    private int room;
    private Date date;
    private int duration;
    private int secSoc;
    private int matricule;


    public static Reservation gets(int room, Reservation reservation) {
        if (!reservations.containsKey(room)) {
            System.out.println("New reservation added.");
            reservations.put(room, reservation);
        } else {
            System.out.println("Reservation already exist");
        }
        return reservations.get(room);
    }

    public Reservation(int room, Date date, int duration, int secSoc, int matricule) {
        this.room = room;
        this.date = date;
        this.duration = duration;
        this.secSoc = secSoc;
        this.matricule = matricule;
    }

    public static void createNewReservationMenu() {
        int matricule;
        int secSoc;
        Date date;
        int duration;
        int room;


        Scanner scanner = new Scanner(System.in);

        try {
            String menu = "===============\n" +
                    "Enter the patient name and security number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 2){
                    try {
                        Map<Integer, Patient> patients = Patient.getPatients();
                        int numero = Integer.parseInt(args[1]);
                        if (patients.containsKey(numero)) {
                            if (patients.get(numero).getName().equalsIgnoreCase(args[0])) {
                                secSoc = numero;
                                break;
                            } else {
                                System.out.println("Ce nom n'existe pas");
                            }
                        } else {
                            System.out.println("Ce numéro de sécurité sociale n'existe pas");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mauvaise syntaxe du numéro de sécurité sociale");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter a Doctor name and Matricule :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 2){
                    try {
                        Map<Integer, Doctor> doctors = Doctor.getDoctors();
                        int numero = Integer.parseInt(args[1]);
                        if (doctors.containsKey(numero)) {
                            if (doctors.get(numero).getName().equalsIgnoreCase(args[0])) {
                                matricule = numero;
                                break;
                            } else {
                                System.out.println("Ce nom n'existe pas");
                            }
                        } else {
                            System.out.println("Ce numéro de matricule n'existe pas");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Mauvaise syntaxe du numéro de matricule");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter a Room number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else {
                    try {
                        int roomConv = Integer.parseInt(args[0]);
                        if (Room.rooms.containsKey(roomConv)) {
                            if (Room.rooms.get(roomConv).equals(false)) {
                                room = roomConv;
                                Room.rooms.replace(room, true);
                                break;
                            } else {
                                System.out.println("Cette salle est déjà occupée");
                            }
                        } else {
                            System.out.println("Cette salle n'existe pas.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Soucis de format");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter Date :\n" +
                    "Format : dd/MM/yyyy HH:mm\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 2){
                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(command);
                        break;
                    } catch (ParseException parseException) {
                        System.out.println("Mauvais format de date");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter durée :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    try {
                        duration = Integer.parseInt(args[0]);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Mauvais format de durée");
                    }
                }
            }

            Reservation.gets(room, new Reservation(room, date, duration, secSoc, matricule));
            //Doctor.gets(matricule, new Doctor(matricule, specialty, degree, hourlyRate, hospital));
        } catch (IllegalStateException e) {
            System.out.println("erreur création Doctor");
        }
    }

}
