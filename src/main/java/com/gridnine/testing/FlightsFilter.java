package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FlightsFilter {
    // This constant is needed because of static nature of LocalDateTime.now()
    public final static LocalDateTime currentTime = LocalDateTime.now();

    public static List<Flight> departureInThePast(List<Flight> flightsList) {
        return flightsList.stream()
                .filter(flight -> flight.getSegments()
                        .stream().anyMatch(segment -> segment
                                .getDepartureDate().isBefore(currentTime))).toList();
    }

    public static List<Flight> arrivalBeforeDeparture(List<Flight> flightsList) {
        return flightsList.stream()
                .filter(flight -> flight.getSegments()
                        .stream().anyMatch(segment -> segment
                                .getDepartureDate().isAfter(segment.getArrivalDate()))).toList();
    }

    public static List<Flight> moreThanTwoHoursOnTheGround(List<Flight> flightsList) {
        List<Flight> filteredFlightsList = new ArrayList<>();

        for (Flight flight : flightsList) {
            List<Segment> segmentList = flight.getSegments();
            long hoursOnTheGround = 0;

            for (int i = 0; i < segmentList.size() - 1; i++) {
                hoursOnTheGround += segmentList.get(i).getArrivalDate()
                        .until(segmentList.get(i + 1).getDepartureDate(), ChronoUnit.HOURS);
            }

            if (hoursOnTheGround > 2) {
                filteredFlightsList.add(flight);
            }
        }
        return filteredFlightsList;
    }
}