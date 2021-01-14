package com.company.reservation;

public class Bill {
    public static void getCost(String string) {
        try {
            int value = Integer.parseInt(string);
            if (Reservation.getReservations().containsKey(value)) {
                Reservation reservation = Reservation.getReservations().get(value);
                int days = reservation.getDuration();
                int addition = days * 55;
                System.out.println("Le coût est de "+addition+".");
            }

        } catch (NumberFormatException e){
            System.out.println("la valeur entrée est erronée");
        }
    }

}