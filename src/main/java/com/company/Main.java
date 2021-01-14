package com.company;

import com.company.appointments.Appointment;
import com.company.reservation.Reservation;
import com.company.rooms.Room;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
        initConfig();
        Room.initRooms();

        String menu = "===============\n" +
                "Choose your command :\n" +
                " > 1 - patient     : Patients menu\n" +
                " > 2 - doctor      : Doctors menu\n" +
                " > 3 - appointment : Appointments menu\n" +
                " > 4 - reservation : Reservations menu and bills\n" +
                "" +
                " > 0 - exit        : leave the program\n" +
                "===============\n";

        try {

            while( true ) {
                System.out.println(menu);
                String command = scanner.nextLine();
                System.out.println(command);
                String[] commandArr = command.split(" ");

                if (command.startsWith("1") || command.startsWith("patient")) {
                    Menus.mainPatientMenu();
                } else if (command.startsWith("2") || command.startsWith("doctor")) {
                    Menus.mainDoctorMenu();
                } else if (command.startsWith("3") || command.startsWith("appointment")) {
                    Menus.appointmentMenu();
                } else if (command.startsWith("4") || command.startsWith("reservation")) {
                    Menus.reservationMenu();
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }


                if (command.startsWith("create reservation")) {
                    Reservation.createNewReservationMenu();
                }
            }

        } catch (IllegalStateException e) {
            System.out.println("erreur main");
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }

    private static void initConfig() {
        try {
            System.out.println();
            FileResourcesUtils.main("configs/areas.yml");
            FileResourcesUtils.main("configs/config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*InputStream resource = Room.class.getClassLoader().getResourceAsStream("configs/config.yml");
        File file = File(resource.toURI)

        YamlConfig config = YamlConfig.load(resource);*/

    }
}
