package com.company.rdv;

import com.company.users.Doctor;
import com.company.users.Patient;

import java.util.Date;

public class Rdv {

    private String place;
    private Date date;
    private int secSoc;
    private int matricule;


    public Rdv(String place, Date date, int secSoc, int matricule) {
        this.place = place;
        this.date = date;
        this.secSoc = secSoc;
        this.matricule = matricule;

        for (int listedMatricule : Doctor.getDoctors().keySet()) {
            if (listedMatricule == matricule) {
                break;
            }
        }

        for (int listedPatient : Patient.getPatients().keySet()) {
            if (listedPatient == secSoc) {
                break;
            }
        }
    }


}
