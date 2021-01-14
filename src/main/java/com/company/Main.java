package com.company;

import com.company.rdv.Rdv;
import com.company.reservation.Reservation;
import com.company.rooms.Room;
import com.company.users.Doctor;
import com.company.users.Patient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {
        initConfig();
        Room.initRooms();

        try {

            String menu = "===============\n" +
                    "Choose your command :\n" +
                    " > create patient <name> <firstName>: Creates a new patient\n" +
                    " > create doctor <name> <firstName>: Creates a new doctor\n" +
                    " > create rendezvous : Creates a new rendez-vous\n" +
                    " > create reservation : Creates a new reservation\n" +
                    " > remove rendezvous : remove an existing rendez-vous\n" +
                    " > exit : leave the program\n" +
                    "===============\n";

            while( true ) {
                System.out.println(menu);
                String command = scanner.nextLine();
                System.out.println(command);
                String[] commandArr = command.split(" ");

                if (command.startsWith("create patient") && commandArr.length > 3) {
                    Patient.createNewPatientMenu(commandArr[2], commandArr[3]);
                } else if (command.startsWith("create doctor") && commandArr.length > 3) {
                    Doctor.createNewDoctorMenu(commandArr[2], commandArr[3]);
                } else if (command.startsWith("create rendezvous")) {
                    Rdv.createNewRdvMenu();
                } else if (command.startsWith("create reservation")) {
                    Reservation.createNewReservationMenu();
                } else if (command.startsWith("remove rendezvous")) {
                    Rdv.removeRdv();
                }else if (command.startsWith("exit")) {
                    return;
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
