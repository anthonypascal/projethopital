package com.company.reservation;

import com.company.rooms.Room;
import com.company.users.Doctor;
import com.company.users.Patient;
import com.company.users.User;

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
                        int matriculeConv = Integer.parseInt(args[0]);
                        if (!reservations.containsKey(matriculeConv)) {
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

    private static boolean userName(Class user, int value) {
        String userName;
        if (user == Patient.class) {
            while (true) {
                userName = stringMenu("patient last name");
                if (userName == null) {
                    return false;
                } else if (Patient.getPatients().get(value).getName().equalsIgnoreCase(userName)) {
                    return true;
                } else {
                    System.out.println("Le nom de famille n'est pas correct. Réessayez");
                }
            }
        } else if (user == Doctor.class) {
            while (true) {
                userName = stringMenu("doctor last name");
                if (userName == null) {
                    return false;
                } else if (Doctor.getDoctors().get(value).getName().equalsIgnoreCase(userName)) {
                    return true;
                } else {
                    System.out.println("Le nom de famille n'est pas correct. Réessayez");
                }
            }
        }
        return false;
    }

    public static void createNewReservationMenu() {
        int matricule;
        int secSoc;
        Date date;
        int duration;
        int room;

        secSoc = userID(Patient.class);
        if (secSoc == -1) {
            return;
        }
        if (!userName(Patient.class, secSoc)) {
            return;
        }
        matricule = userID(Doctor.class);
        if (matricule == -1) {
            return;
        }
        if (!userName(Doctor.class, matricule)) {
            return;
        }
        room = intMenu("room");
        if (room == -1) {
            return;
        }

        duration = intMenu("duration (in days)");
        if (duration == -1) {
            return;
        }

        date = dateMenu();
        if (date == null) {
            return;
        }
        Reservation.gets(room, new Reservation(room, date, duration, secSoc, matricule));
    }

    public static void removeReservation() {
        Scanner scanner = new Scanner(System.in);
        String menu = "======================\n" +
                "Enter Room ID\n" +
                "======================";


        while (true) {
            System.out.println(menu);
            String command = scanner.nextLine();
            String[] args = command.split(" ");

            if (command.startsWith("exit")) {
                return;
            } else if (args.length == 1) {
                try {
                    int value = Integer.parseInt(args[0]);

                    if (Room.getRooms().containsKey(value)) {
                        if (reservations.containsKey(value)) {
                            System.out.println("Réservation supprimée");
                            reservations.remove(value);
                            Room.getRooms().replace(value, true);
                            break;
                        } else {
                            System.out.println("Cette Room n'est pas réservée");
                        }
                    } else {
                        System.out.println("Cette chambre n'existe pas");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Mauvaise syntaxe de numéro de Room");
                }
            }
        }
    }

    public static Map<Integer, Reservation> getReservations() {
        return reservations;
    }

    public int getDuration() {
        return duration;
    }

    public Date getDate() {
        return date;
    }

    public int getMatricule() {
        return matricule;
    }

    public int getRoom() {
        return room;
    }

    public int getSecSoc() {
        return secSoc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setSecSoc(int secSoc) {
        this.secSoc = secSoc;
    }
}
