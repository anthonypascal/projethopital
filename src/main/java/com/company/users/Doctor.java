package com.company.users;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Doctor extends User {

    private static Map<Integer, Doctor> doctors = new HashMap<Integer, Doctor>();

    private int identificationNumber;
    private String specialty;
    private String degree;
    private String hourlyRate;
    private String hospital;

    public static Doctor gets(Integer integer, Doctor doctor) {
        if (!doctors.containsKey(integer)) {
            System.out.println("Created new doctor");
            doctors.put(integer, doctor);
        }
        return doctors.get(integer);
    }

    public Doctor(int identificationNumber, String specialty, String degree, String hourlyRate, String hospital) {

        this.identificationNumber = identificationNumber;
        this.specialty = specialty;
        this.degree = degree;
        this.hourlyRate = hourlyRate;
        this.hospital = hospital;
    }

    /*GETTER*/
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public String getHospital() { return hospital; }

    public static Map<Integer, Doctor> getPatients() {
        return doctors;
    }

    /*SETTER*/
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setIdentificationNumber(int identificationNumber) { this.identificationNumber = identificationNumber; }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public static void createNewDoctorMenu(String lastName, String firstName) {
        System.out.println(lastName + " " + firstName);

        int identificationNumber = 0;
        String specialty;
        String degree;
        String hourlyRate;
        String hospital;

        Scanner scanner = new Scanner(System.in);

        try {
            String menu = "===============\n" +
                    "Enter specialty :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else {
                    specialty = args[0];
                    break;
                }
            }

            menu = "===============\n" +
                    "Enter degree :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    degree = command;
                    break;
                }
            }

            menu = "===============\n" +
                    "Enter Hourly Rate :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0){
                    hourlyRate = command;
                    break;
                }
            }

            menu = "===============\n" +
                    "Enter Hospital :\n" +
                    "===============\n";
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length > 0) {
                    hospital = command;
                    break;
                }
            }

            Doctor.gets(identificationNumber, new Doctor(identificationNumber, specialty, degree, hourlyRate, hospital));
        } catch (IllegalStateException e){
            System.out.println("erreur création Doctor");
        }

    }
}
