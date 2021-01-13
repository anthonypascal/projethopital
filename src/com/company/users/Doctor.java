package com.company.users;

import java.util.HashMap;
import java.util.Map;

public class Doctor extends User {

    private static Map<Integer, Doctor> doctors = new HashMap<Integer, Doctor>();

    private int identificationNumber;
    private String specialty;
    private String degree;
    private String hourlyRate;
    private String hospital;

    public Doctor gets(Integer integer, Doctor doctor) {
        if (!doctors.containsKey(integer)) {
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
}
