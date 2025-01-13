package com.gridnine.testing;

public class Main {
    public static void main(String[] args) {
        FlightListWrapper flightListWrapper = new FlightListWrapper(FlightBuilder.createFlights());
        System.out.println("All flights:");
        System.out.println(flightListWrapper.getFlightsList() + "\n");

        System.out.println("1. Already departed flights are excluded");
        System.out.println(flightListWrapper.departureInThePastFilter().applyFiltersAndReturnFilteredFlights() + "\n");

        System.out.println("2. Flights with arrival before departure are excluded");
        System.out.println(flightListWrapper.arrivalBeforeDepartureFilter().applyFiltersAndReturnFilteredFlights() + "\n");

        System.out.println("3. Flights with more than 2 hours on the ground are excluded");
        System.out.println(flightListWrapper.moreThanTwoHoursOnTheGroundFilter().applyFiltersAndReturnFilteredFlights());
    }
}