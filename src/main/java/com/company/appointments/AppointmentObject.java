package com.company.appointments;

import java.util.Date;

public class AppointmentObject {

    private String name;
    private String place;
    private Date date;
    private int secSoc;
    private int matricule;

    AppointmentObject(String name, String place, Date date, int secSoc, int matricule) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.secSoc = secSoc;
        this.matricule = matricule;
    }

    public int getSecSoc() {
        return secSoc;
    }

    public int getMatricule() {
        return matricule;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public AppointmentObject setMatricule(int matricule) {
        this.matricule = matricule;
        return this;
    }

    public AppointmentObject setSecSoc(int secSoc) {
        this.secSoc = secSoc;
        return this;
    }

    public AppointmentObject setName(String name) {
        this.name = name;
        return this;
    }

    public AppointmentObject setDate(Date date) {
        this.date = date;
        return this;
    }

    public AppointmentObject setPlace(String place) {
        this.place = place;
        return this;
    }
}
