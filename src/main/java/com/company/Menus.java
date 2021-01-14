package com.company;

import com.company.appointments.Appointment;
import com.company.reservation.Bill;
import com.company.reservation.Reservation;
import com.company.users.Doctor;
import com.company.users.Patient;

import java.util.Scanner;

public class Menus {
    public static void mainPatientMenu() {
        Scanner scanner = new Scanner(System.in);

        String menu = "===============\n" +
                "Patients Menu :\n" +
                "> 1 - display : Display patients Menu\n" +
                "> 2 - create  : Create new patient\n" +
                "> 3 - edit    : Edit patient Menu\n" +
                "> 4 - delete  : Delete a patient\n" +
                "" +
                "> 0 - exit    : Back to main Menu\n" +
                "===============\n";

        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("display")) {
                    System.out.println("display menu");
                } else if (command.startsWith("2") || command.startsWith("create")) {
                    Patient.createNewPatientMenu();
                } else if (command.startsWith("3") || command.startsWith("edit")) {
                    System.out.println("edit menu");
                } else if (command.startsWith("4") || command.startsWith("delete")) {
                    System.out.println("delete menu");
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Illegal exception Patient Menu");
        }
    }


    public static void mainDoctorMenu() {
        Scanner scanner = new Scanner(System.in);

        String menu = "===============\n" +
                "Doctors Menu :\n" +
                "> 1 - display : Display doctors Menu\n" +
                "> 2 - create  : Create new doctor\n" +
                "> 3 - edit    : Edit doctor Menu\n" +
                "> 4 - delete  : Delete a doctor\n" +
                "" +
                "> 0 - exit    : Back to main Menu\n" +
                "===============\n";

        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("display")) {
                    System.out.println("display menu");
                } else if (command.startsWith("2") || command.startsWith("create")) {
                    Doctor.createNewDoctorMenu();
                } else if (command.startsWith("3") || command.startsWith("edit")) {
                    System.out.println("edit menu");
                } else if (command.startsWith("4") || command.startsWith("delete")) {
                    System.out.println("delete menu");
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Illegal exception Doctor Menu");
        }
    }

    public static void appointmentMenu() {
        Scanner scanner = new Scanner(System.in);

        String menu = "===============\n" +
                "Appointments Menu :\n" +
                "> 1 - display : Display appointments Menu\n" +
                "> 2 - create  : Create new appointment\n" +
                "> 3 - edit    : Edit appoint Menu\n" +
                "> 4 - delete  : cancel an appointment\n" +
                "" +
                "> 0 - exit    : Back to main Menu\n" +
                "===============\n";

        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("display")) {
                    System.out.println("display menu");
                } else if (command.startsWith("2") || command.startsWith("create")) {
                    Appointment.createNewAppointmentMenu();
                } else if (command.startsWith("3") || command.startsWith("edit")) {
                    System.out.println("edit menu");
                } else if (command.startsWith("4") || command.startsWith("delete")) {
                    Appointment.removeAppointment();
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Illegal exception Appointment Menu");
        }
    }

    public static void reservationMenu() {
        Scanner scanner = new Scanner(System.in);

        String menu = "===============\n" +
                "Reservations Menu :\n" +
                "> 1 - display              : Display reservation Menu\n" +
                "> 2 - create               : Create new reservation\n" +
                "> 3 - edit                 : Edit reservation Menu\n" +
                "> 4 - delete               : cancel an reservation\n" +
                "> 5 - bill <room number>   : Generate a reservation bill\n" +
                "" +
                "> 0 - exit                 : Back to main Menu\n" +
                "===============\n";

        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("display")) {
                    System.out.println("display menu");
                } else if (command.startsWith("2") || command.startsWith("create")) {
                    Reservation.createNewReservationMenu();
                } else if (command.startsWith("3") || command.startsWith("edit")) {
                    System.out.println("edit menu");
                } else if (command.startsWith("4") || command.startsWith("delete")) {
                    Reservation.removeReservation();
                } else if ((command.startsWith("5") || command.startsWith("bill")) && args.length == 2) {
                    Bill.getCost(args[1]);
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("Illegal exception Appointment Menu");
        }
    }
}
