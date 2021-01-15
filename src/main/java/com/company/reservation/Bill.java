package com.company.reservation;

import com.company.users.Patient;
import com.company.users.usersobjects.PatientObject;

public class Bill {
    public static void getCost(String string) {
        try {
            int value = Integer.parseInt(string);
            if (Reservation.getReservations().containsKey(value)) {
                ReservationObject reservation = Reservation.getReservations().get(value);
                int days = reservation.getDuration();
                int addition = days * 55;
                PatientObject patient = Patient.getPatients().get(reservation.getSecSoc());
                System.out.println("Facture au nom de " + patient.getName() + " " + patient.getFirstName());
                System.out.println("Le nombre de jours est de " + days);
                System.out.println("Le coût est de " + addition + "€.");
            } else {
                System.out.println("Il n'y a aucune réservation actuellement avec cette chambre");
            }

        } catch (NumberFormatException e){
            System.out.println("la valeur entrée est erronée");
        }
    }

}