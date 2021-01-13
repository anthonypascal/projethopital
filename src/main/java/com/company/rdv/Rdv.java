package com.company.rdv;

import com.company.users.Doctor;
import com.company.users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Rdv {

    private String place;
    private Date date;
    private int secSoc;
    private int matricule;


    public Rdv(String place, Date date, int secSoc, int matricule) {
        this.place = place;
        this.date = date;
        this.secSoc = secSoc;
        this.matricule = matricule;
    }

    public static void createNewDoctorMenu() {
        System.out.println();

        int matricule;
        int secSoc;
        Date date;


        Scanner scanner = new Scanner(System.in);

        try {

            String menu = "===============\n" +
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
                        boolean isExist = false;
                        for (int listedDoctor : Doctor.getDoctors().keySet()) {
                            if (listedDoctor == matriculeConv) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            matricule = matriculeConv;
                            break;
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
                        boolean isExist = false;
                        for (int listedPatient : Patient.getPatients().keySet()) {
                            if (listedPatient == secSocConv) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            secSoc = secSocConv;
                            break;
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
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(command);
                        System.out.println(args[0] + "\t" + date1);
                        break;
                    } catch (ParseException parseException) {
                        System.out.println("Mauvais format de date");
                    }
                }
            }

            //Doctor.gets(matricule, new Doctor(matricule, specialty, degree, hourlyRate, hospital));
        } catch (IllegalStateException e) {
            System.out.println("erreur création Doctor");
        }
    }
}
