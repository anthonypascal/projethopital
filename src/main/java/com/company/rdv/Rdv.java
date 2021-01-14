package com.company.rdv;

import com.company.rooms.Room;
import com.company.users.Doctor;
import com.company.users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Rdv {

    private static final Map<String, Rdv> rendezVous = new HashMap<>();

    private String name;
    private String place;
    private Date date;
    private int secSoc;
    private int matricule;


    public static Rdv gets(String name, Rdv rdv) {
        if (!rendezVous.containsKey(name)) {
            System.out.println("New rdv added.");
            rendezVous.put(name, rdv);
        } else {
            System.out.println("Rdv already exist");
        }
        return rendezVous.get(name);
    }

    public Rdv(String name, String place, Date date, int secSoc, int matricule) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.secSoc = secSoc;
        this.matricule = matricule;
    }

    public static void createNewRdvMenu() {
        int matricule;
        int secSoc;
        Date date;
        String name;
        String place;


        Scanner scanner = new Scanner(System.in);

        try {
            String menu = "===============\n" +
                    "Enter a rendez-vous name :\n" +
                    "in one word :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    if (!rendezVous.containsKey(command)) {
                        name = command;
                        break;
                    } else {
                        System.out.println("Ce nom de rendez-vous existe déjà");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter a Doctor Matricule :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    try {
                        int matriculeConv = Integer.parseInt(args[0]);
                        if (Doctor.getDoctors().containsKey(matriculeConv)) {
                            matricule = matriculeConv;
                            break;
                        } else {
                            System.out.println("Ce médecin n'existe pas");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Soucis de format");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter a Social Security Number :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else {
                    try {
                        int secSocConv = Integer.parseInt(args[0]);
                        if (Patient.getPatients().containsKey(secSocConv)) {
                            secSoc = secSocConv;
                            break;
                        } else {
                            System.out.println("Ce patient n'existe pas");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Soucis de format");
                    }
                }
            }

            menu = "===============\n" +
                    "Enter a place :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    place = command;
                    break;
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
                        boolean bool = false;

                        for (Rdv listedRdv : rendezVous.values()) {
                            if (listedRdv.date == date) {
                                if (listedRdv.matricule == matricule || listedRdv.secSoc == secSoc) {
                                    System.out.println("L'un des deux participant possède déjà un rendez-vous à la même heure !");
                                    bool = true;
                                }
                            }
                        }

                        if (!bool) {
                            System.out.println(args[0] + "\t" + date);
                            break;
                        }
                    } catch (ParseException parseException) {
                        System.out.println("Mauvais format de date");
                    }
                }
            }

            Rdv.gets(name, new Rdv(name, place, date, secSoc, matricule));
            //Doctor.gets(matricule, new Doctor(matricule, specialty, degree, hourlyRate, hospital));
        } catch (IllegalStateException e) {
            System.out.println("erreur création Doctor");
        }
    }

    public static void removeRdv() {

        Scanner scanner = new Scanner(System.in);
        try {
            String menu = "===============\n" +
                    "Enter The Rdv name to delete :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    if (rendezVous.containsKey(command)) {
                        rendezVous.remove(command);
                        System.out.println("rendezvous supprimé");
                        break;
                    } else {
                        System.out.println("Le rendez vous indiqué n'existe pas");
                    }

                }
            }


        } catch (IllegalStateException e){
            System.out.println("erreur création Patient");
        }
    }
}
