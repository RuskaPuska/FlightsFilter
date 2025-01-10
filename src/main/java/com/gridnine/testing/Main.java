package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightsList = FlightBuilder.createFlights();
        System.out.println("All flights:");
        System.out.println(flightsList + "\n");

        System.out.println("1. Already departed flights are excluded");
        System.out.println(FlightsFilter.departureInThePast(flightsList) + "\n");

        System.out.println("2. Flights with arrival before departure are excluded");
        System.out.println(FlightsFilter.arrivingBeforeDeparture(flightsList) + "\n");

        System.out.println("3. Flights with more than 2 hours on the ground are excluded");
        System.out.println(FlightsFilter.moreThanTwoHoursOnTheGround(flightsList));
    }
}