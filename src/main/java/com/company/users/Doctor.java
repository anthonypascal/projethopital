package com.company.users;

import com.company.users.usersobjects.DoctorObject;

import java.util.*;

public class Doctor {

    private static final Map<Integer, DoctorObject> doctors = new HashMap<Integer, DoctorObject>();

    public static DoctorObject gets(Integer integer, DoctorObject doctor) {
        if (!doctors.containsKey(integer)) {
            System.out.println("Created new doctor");
            doctors.put(integer, doctor);
        }
        return doctors.get(integer);
    }

    public static Map<Integer, DoctorObject> getDoctors() {
        return doctors;
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

    private static int matriculeMenu() {
        Scanner scanner = new Scanner(System.in);
        try {

            String menu = "===============\n" +
                    "Enter an identification number :\n" +
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
                        if (!Doctor.doctors.containsKey(matriculeConv)) {
                            return matriculeConv;
                        } else {
                            System.out.println("Ce matricule de médecin est déjà utilisé !");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("mauvais format de texte");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument matricule menu");
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

    public static void createNewDoctorMenu() {
        int matricule;
        String lastName;
        String firstName;
        List<String> specialty;
        String degree;
        int hourlyRate;
        String hospital;

        matricule = matriculeMenu();
        if (matricule == -1) {
            return;
        }

        lastName = stringMenu("last name");
        if (lastName == null) {
            return;
        }

        firstName = stringMenu("first name");
        if (firstName == null) {
            return;
        }

        String resultSpec = stringMenu("specialty");
        if (resultSpec == null) {
            return;
        }
        specialty = Arrays.asList(resultSpec.split(" "));

        degree = stringMenu("degree");
        if (degree == null) {
            return;
        }

        hourlyRate = intMenu("hourly rate");
        if (hourlyRate == -1) {
            return;
        }

        hospital = stringMenu("hospital name");
        if (hospital == null) {
            return;
        }

        Doctor.gets(matricule, new DoctorObject(matricule, lastName, firstName, degree, hourlyRate, hospital, specialty));
    }

    public static void display(String matricule) {
        System.out.println("MATRICULE     | NOM        | PRENOM      | SPECIALITY   | DEGREE     | HOURLY RATE    | HOSPITALS     ");
        if (matricule != null) {
            try {
                if (doctors.containsKey(Integer.parseInt(matricule))) {
                    DoctorObject doctor = doctors.get(Integer.parseInt(matricule));
                    System.out.println(doctor.getMatricule() + " | " + doctor.getName() + " | " + doctor.getFirstName() +
                            " | " + doctor.getSpecialty().toString() +
                            " | " + doctor.getDegree() + " | " + doctor.getHourlyRate() +
                            " | " + doctor.getHospital());
                } else {
                    System.out.println("Ce docteur n'existe pas");
                }
            } catch (NumberFormatException e) {
                System.out.println("Mauvaise syntaxe sur l'affichage d'un docteur");
            }
        } else {
            System.out.println("");
            for (DoctorObject doctor : getDoctors().values()) {
                System.out.println(doctor.getMatricule() + " | " + doctor.getName() + " | " + doctor.getFirstName() +
                        " | " + doctor.getSpecialty().toString() + " | " + doctor.getDegree() + " | " + doctor.getHourlyRate() +
                        " | " + doctor.getHospital());
            }
        }
    }

    public static void removeDoctor() {

        Scanner scanner = new Scanner(System.in);
        try {
            String menu = "===============\n" +
                    "Enter the Doctor matricule to delete :\n" +
                    "===============\n";

            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("exit")) {
                    return;
                } else if (args.length == 1){
                    try {
                        int matricule = Integer.parseInt(args[0]);
                        if (doctors.containsKey(matricule)) {
                            doctors.remove(matricule);
                            System.out.println("Deleted doctor");
                            break;
                        } else {
                            System.out.println("The specified doctor doesn't exist");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Delete doctor NumberFormatExeption");
                    }

                }
            }

        } catch (IllegalStateException e){
            System.out.println("Error while creating appointment");
        }
    }

    public static void editDoctor(String oldMatricule) {
        DoctorObject doctor;
        try {
            int testMatricule = Integer.parseInt(oldMatricule);
            if (getDoctors().containsKey(testMatricule)) {
                doctor = getDoctors().get(testMatricule);
            } else {
                System.out.println("Ce docteur n'existe pas");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Soucis de format edit doctor");
            return;
        }

        String menu = "===============\n" +
                "1 - matricule : change matricule of the doctor\n" +
                "2 - specialty : change specialty of the doctor\n" +
                "3 - degree : change degree of the doctor\n" +
                "4 - hourlyRate : change hourly of the doctor\n" +
                "5 - hospital : change hospital\n" +
                "0 - exit : back to the previous menu\n" +
                "===============\n";
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println(menu);
                String command = scanner.nextLine();
                String[] args = command.split(" ");

                if (command.startsWith("1") || command.startsWith("matricule")) {
                    int matricule = matriculeMenu();
                    if (matricule == -1) {
                        System.out.println("Annulation");
                    } else {
                        doctors.remove(doctor.getMatricule());
                        doctors.put(doctor.getMatricule(), doctor.setMatricule(matricule));
                        System.out.println("edited matricule");
                    }
                } else if (command.startsWith("2") || command.startsWith("specialty")) {
                    String specialty = stringMenu("specialty");
                    if (specialty != null) {
                        doctors.replace(doctor.getMatricule(), doctor.setSpecialty(Arrays.asList(specialty.split(" "))));
                        System.out.println("edited specialty");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("3") || command.startsWith("degree")) {
                    String degree = stringMenu("degree");
                    if (degree != null) {
                        doctors.replace(doctor.getMatricule(), doctor.setDegree(degree));
                        System.out.println("edited degree");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("4") || command.startsWith("hourlyRate")) {
                    int hourlyRate = intMenu( "hourlyRate");
                    if (hourlyRate != -1) {
                        doctors.replace(doctor.getMatricule(), doctor.setHourlyRate(hourlyRate));
                        System.out.println("edited hourlyRate");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("5") || command.startsWith("hospital")) {
                    String hospital = stringMenu("hospital");
                    if (hospital != null) {
                        doctors.replace(doctor.getMatricule(), doctor.setHospital(hospital));
                        System.out.println("hospital edited");
                    } else {
                        System.out.println("Annulation");
                    }
                } else if (command.startsWith("0") || command.startsWith("exit")) {
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument edit patient menu");
        }
    }
}
