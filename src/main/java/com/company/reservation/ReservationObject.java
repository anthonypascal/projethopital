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

    public int getDuration() {
        return duration;
    }

    public Date getDate() {
        return date;
    }

    public int getMatricule() {
        return matricule;
    }

    public int getRoom() {
        return room;
    }

    public int getSecSoc() {
        return secSoc;
    }

    public ReservationObject setDate(Date date) {
        this.date = date;
        return this;
    }

    public ReservationObject setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public ReservationObject setMatricule(int matricule) {
        this.matricule = matricule;
        return this;
    }

    public ReservationObject setRoom(int room) {
        this.room = room;
        return this;
    }

    public ReservationObject setSecSoc(int secSoc) {
        this.secSoc = secSoc;
        return this;
    }
}
