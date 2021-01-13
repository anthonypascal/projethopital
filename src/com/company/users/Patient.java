package com.company.users;

import java.util.HashMap;
import java.util.Map;

public class Patient extends User {

    private static Map<Integer, Patient> patients = new HashMap<Integer, Patient>();

    private String secSoc;
    private String postalAdress;
    private String phoneNumber;
    private String mailAdress;

    public Patient gets(Integer integer, Patient patient) {
        if (!patients.containsKey(integer)) {
            patients.put(integer, patient);
        }
        return patients.get(integer);
    }
    public Patient() {
        setName("Bardo");
        setFirstName("Brigitte");
        setMailAdress("brigitte@bardo.conne");
        setPhoneNumber("+33012345678");
        setPostalAdress("1 Rue de la paie");
        setSecSoc("11111111111");
        Patient patient = gets(patients.size(), this);

        System.out.println(patient.getName() + " " + patient.getFirstName());
        System.out.println(patient.getMailAdress() + " " + patient.getPhoneNumber());
        System.out.println(patient.getPostalAdress() + " " + patient.getSecSoc());
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getSecSoc() {
        return secSoc;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalAdress() {
        return postalAdress;
    }

    public static Map<Integer, Patient> getPatients() {
        return patients;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPostalAdress(String postalAdress) {
        this.postalAdress = postalAdress;
    }

    public void setSecSoc(String secSoc) {
        this.secSoc = secSoc;
    }
}
