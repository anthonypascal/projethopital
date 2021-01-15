package com.company.users;

import java.util.*;

public class Doctor extends User {

    private static final Map<Integer, Doctor> doctors = new HashMap<Integer, Doctor>();

    private int matricule;
    private List<String> specialty;
    private String degree;
    private int hourlyRate;
    private String hospital;

    public static Doctor gets(Integer integer, Doctor doctor) {
        if (!doctors.containsKey(integer)) {
            System.out.println("Created new doctor");
            doctors.put(integer, doctor);
        }
        return doctors.get(integer);
    }

    public Doctor(String lastName, String firstName, int matricule, List<String> specialty, String degree, int hourlyRate, String hospital) {
        setName(lastName);
        setFirstName(firstName);
        this.matricule = matricule;
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

    public int getMatricule() {
        return matricule;
    }

    public List<String> getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public String getHospital() { return hospital; }

    public static Map<Integer, Doctor> getDoctors() {
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

    public void setMatricule(int matricule) { this.matricule = matricule; }

    public void setSpecialty(List<String> specialty) {
        this.specialty = specialty;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
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

        Doctor.gets(matricule, new Doctor(lastName, firstName, matricule, specialty, degree, hourlyRate, hospital));
    }

    public static void display(String matricule) {
        System.out.println("MATRICULE     | NOM        | PRENOM      | SPECIALITY   | DEGREE     | HOURLY RATE    | HOSPITALS     ");
        if (matricule != null) {
            try {
                if (doctors.containsKey(Integer.parseInt(matricule))) {
                    Doctor doctor = doctors.get(Integer.parseInt(matricule));
                    System.out.println(doctor.getMatricule() + " | " + doctor.getName() + " | " + doctor.getFirstName() +
                            " | " +
                            doctor.getSpecialty().toString().replaceAll("\\[", "").replaceAll("]", "") +
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
            for (Doctor doctor : getDoctors().values()) {
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
}
