package com.company.reservation;

import com.company.users.Patient;

public class Bill {
    public static void getCost(String string) {
        try {
            int value = Integer.parseInt(string);
            if (Reservation.getReservations().containsKey(value)) {
                Reservation reservation = Reservation.getReservations().get(value);
                int days = reservation.getDuration();
                int addition = days * 55;
                System.out.println("Facture au nom de " + Patient.getPatients().get(reservation.getSecSoc()));
                System.out.println("Le nombre de jours est de " + days);
                System.out.println("Le coût est de "+addition+"€.");
            }

        } catch (NumberFormatException e){
            System.out.println("la valeur entrée est erronée");
        }
    }

}