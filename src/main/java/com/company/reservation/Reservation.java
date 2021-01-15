package com.company.reservation;

import com.company.rooms.Room;
import com.company.users.Doctor;
import com.company.users.Patient;
import com.company.users.usersobjects.PatientObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reservation {
    private static final Map<Integer, ReservationObject> reservationsObject = new HashMap<>();


    public static ReservationObject gets(int room, ReservationObject reservation) {
        if (!reservationsObject.containsKey(room)) {
            System.out.println("New reservation added.");
            reservationsObject.put(room, reservation);
        } else {
            System.out.println("Reservation already exist");
        }
        return reservationsObject.get(room);
    }

    private static int roomMenu(String what) {
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
                        if (!reservationsObject.containsKey(matriculeConv)) {
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
                userID = roomMenu("patient Social Security number");
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
                userID = roomMenu("doctor matricule");
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
        room = roomMenu("room");
        if (room == -1) {
            return;
        }

        duration = roomMenu("duration (in days)");
        if (duration == -1) {
            return;
        }

        date = dateMenu();
        if (date == null) {
            return;
        }
        Reservation.gets(room, new ReservationObject(room, date, duration, secSoc, matricule));
        reservationsObject.put(room, new ReservationObject(room, date, duration, secSoc, matricule));
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
                        if (reservationsObject.containsKey(value)) {
                            System.out.println("Réservation supprimée");
                            reservationsObject.remove(value);
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

    public static void display(String room) {
        System.out.println("ROOM     | DATE        | DURATION      | SEC SOC   | MATRICULE     |");
        if (room != null) {
            try {
                if (reservationsObject.containsKey(Integer.parseInt(room))) {
                    ReservationObject reservation = reservationsObject.get(Integer.parseInt(room));
                    System.out.println(reservation.getRoom() + " | " + reservation.getDate() + " | " + reservation.getDuration() +
                            " | " + reservation.getSecSoc() + " | " + reservation.getMatricule());
                } else {
                    System.out.println("Ce patient n'existe pas");
                }
            } catch (NumberFormatException e) {
                System.out.println("Mauvaise syntaxe sur l'affichage d'un patient");
            }
        } else {
            System.out.println("");
            for (ReservationObject reservation : reservationsObject.values()) {
                System.out.println(reservation.getRoom() + " | " + reservation.getDate() + " | " + reservation.getDuration() +
                        " | " + reservation.getSecSoc() + " | " + reservation.getMatricule());
            }
        }
    }

    public static void editReservation(String oldSecSoc) {
        ReservationObject reservation;
        try {
            int testSecSoc = Integer.parseInt(oldSecSoc);
            if (Reservation.getReservations().containsKey(testSecSoc)) {
                reservation = Reservation.getReservations().get(testSecSoc);
            } else {
                System.out.println("Cette réservation n'existe pas");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Soucis de format edit reservation");
            return;
        }

        String menu = "===============\n" +
                "1 - room : change room" +
                "2 - date : change date" +
                "3 - secsoc : change social secutiry number\n" +
                "4 - matricule : change matricule number\n" +
                "5 - duration : change duration in days\n" +
                "" +
                "0 - exit : back to the previous menu\n" +
                "===============\n";
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("room")) {
                    int room = roomMenu("room");
                    if (room == -1) {
                        System.out.println("Annulation");
                    } else {
                        reservationsObject.remove(reservation.getRoom());
                        reservationsObject.put(room, reservation.setRoom(room));
                        System.out.println("edited room number");
                    }
                } else if (command.startsWith("2") || command.startsWith("date")) {
                    Date date = dateMenu();
                    if (date != null) {
                        reservationsObject.replace(reservation.getRoom(), reservation.setDate(date));
                        System.out.println("edited date");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("3") || command.startsWith("secSoc")) {
                    int secSoc = userID(Patient.class);
                    if (secSoc == -1) {
                        System.out.println("Annulation");
                        return;
                    }
                    reservationsObject.replace(reservation.getRoom(), reservation.setSecSoc(secSoc));

                } else if (command.startsWith("4") || command.startsWith("matricule")) {
                    int matricule = userID(Doctor.class);
                    if (matricule == -1) {
                        System.out.println("Annulation");
                        return;
                    }
                    reservationsObject.replace(reservation.getRoom(), reservation.setMatricule(matricule));
                } else if (command.startsWith("5") || command.startsWith("duration")) {
                    int duration = intMenu("duration in days");
                    if (duration == -1) {
                        System.out.println("Annulation");
                        return;
                    }
                    reservation.setDuration(duration);
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument edit reservation menu");
        }
    }

    public static Map<Integer, ReservationObject> getReservations() {
        return reservationsObject;
    }
}
