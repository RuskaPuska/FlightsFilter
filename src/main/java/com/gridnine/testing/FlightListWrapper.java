package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public class FlightListWrapper {
    private List<Flight> flightList;
    private List<Flight> flightsToBeDeleted = new ArrayList<>();
    private List<Flight> filteredFlights;

    public FlightListWrapper(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightsList() {
        return flightList;
    }

    public void setFlightsList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public FlightListWrapper departureInThePastFilter() {
        if (flightsToBeDeleted.isEmpty()) {
            flightsToBeDeleted.addAll(FlightsFilter.departureInThePast(flightList));
        }

        List<Flight> tempFlightList = new ArrayList<>(FlightsFilter.departureInThePast(flightList));
        flightsToBeDeleted.retainAll(tempFlightList);
        return this;
    }

    public FlightListWrapper arrivalBeforeDepartureFilter() {
        if (flightsToBeDeleted.isEmpty()) {
            flightsToBeDeleted.addAll(FlightsFilter.arrivalBeforeDeparture(flightList));
        }

        List<Flight> tempFlightList = new ArrayList<>(FlightsFilter.arrivalBeforeDeparture(flightList));
        flightsToBeDeleted.retainAll(tempFlightList);
        return this;
    }

    public FlightListWrapper moreThanTwoHoursOnTheGroundFilter() {
        if (flightsToBeDeleted.isEmpty()) {
            flightsToBeDeleted.addAll(FlightsFilter.moreThanTwoHoursOnTheGround(flightList));
        }

        List<Flight> tempFlightList = new ArrayList<>(FlightsFilter.moreThanTwoHoursOnTheGround(flightList));
        flightsToBeDeleted.retainAll(tempFlightList);
        return this;
    }

    public List<Flight> applyFiltersAndReturnFilteredFlights() {
        filteredFlights = new ArrayList<>(flightList);
        filteredFlights.removeAll(flightsToBeDeleted);
        flightsToBeDeleted.clear();
        return filteredFlights;
    }
}