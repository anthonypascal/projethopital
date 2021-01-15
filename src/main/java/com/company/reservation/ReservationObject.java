package com.company.reservation;

import java.util.Date;

public class ReservationObject {

    private int room;
    private Date date;
    private int duration;
    private int secSoc;
    private int matricule;

    ReservationObject(int room, Date date, int duration, int secSoc, int matricule) {
        this.room = room;
        this.date = date;
        this.duration = duration;
        this.secSoc = secSoc;
        this.matricule = matricule;
    }
}
