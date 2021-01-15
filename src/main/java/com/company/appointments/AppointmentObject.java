package com.company.appointments;

import com.company.appointments.Appointment;

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
}
