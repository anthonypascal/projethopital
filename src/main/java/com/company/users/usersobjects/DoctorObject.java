package com.company.users.usersobjects;

import java.util.List;

public class DoctorObject extends UserObject {
    private int matricule;
    private List<String> specialty;
    private String degree;
    private int hourlyRate;
    private String hospital;
    public DoctorObject(int matricule, String name, String firstName, String degree, int hourlyRate, String hospital, List<String> specialty) {
        super(name, firstName);
        this.matricule = matricule;
        this.specialty = specialty;
        this.degree = degree;
        this.hourlyRate = hourlyRate;
        this.hospital = hospital;
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public int getMatricule() {
        return matricule;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public String getDegree() {
        return degree;
    }

    public String getHospital() {
        return hospital;
    }

    public List<String> getSpecialty() {
        return specialty;
    }

    @Override
    public PatientObject setFirstName(String firstName) {
        super.setFirstName(firstName);
        return null;
    }

    @Override
    public PatientObject setName(String name) {
        super.setName(name);
        return null;
    }

    public DoctorObject setMatricule(int matricule) {
        this.matricule = matricule;
        return this;
    }

    public DoctorObject setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public DoctorObject setHospital(String hospital) {
        this.hospital = hospital;
        return this;
    }

    public DoctorObject setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
        return this;
    }

    public DoctorObject setSpecialty(List<String> specialty) {
        this.specialty = specialty;
        return this;
    }
}
