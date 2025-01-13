package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterTest {
    // Test flight with exactly 2 hours on the ground
    Flight testFlight1 = new Flight(List.of(
            new Segment(FlightsFilter.currentTime, FlightsFilter.currentTime.plusHours(2)),
            new Segment(FlightsFilter.currentTime.plusHours(4), FlightsFilter.currentTime.plusHours(8))));

    // Test flight with departure before current moment
    Flight testFlight2 = new Flight(List.of(
            new Segment(FlightsFilter.currentTime.minusHours(5), FlightsFilter.currentTime)));

    // Test flight with arrival before departure
    Flight testFlight3 = new Flight(List.of(
            new Segment(FlightsFilter.currentTime, FlightsFilter.currentTime.minusHours(3))));

    // Test flight with more than 2 hours on the ground
    Flight testFlight4 = new Flight(List.of(
            new Segment(FlightsFilter.currentTime, FlightsFilter.currentTime.plusHours(2)),
            new Segment(FlightsFilter.currentTime.plusHours(5), FlightsFilter.currentTime.plusHours(8))));

    // Test flight with exactly 2 hours on the ground and departure before current moment
    Flight testFlight5 = new Flight(List.of(
            new Segment(FlightsFilter.currentTime.minusHours(3), FlightsFilter.currentTime),
            new Segment(FlightsFilter.currentTime.plusHours(4), FlightsFilter.currentTime.plusHours(6))));

    public List<Flight> getAllTestFlights() {
        return new ArrayList<>(List.of(testFlight1, testFlight2, testFlight3, testFlight4, testFlight5));
    }

    @Test
    public void departureInThePastTest() {
        List<Flight> expected = List.of(testFlight2, testFlight5);

        FlightListWrapper tempWrapper = new FlightListWrapper(getAllTestFlights());

        List<Flight> actual = getAllTestFlights();
        actual.removeAll(tempWrapper.departureInThePastFilter().applyFiltersAndReturnFilteredFlights());

        assertEquals(expected, actual);
    }

    @Test
    public void arrivalBeforeDepartureTest() {
        List<Flight> expected = List.of(testFlight3);

        FlightListWrapper tempWrapper = new FlightListWrapper(getAllTestFlights());

        List<Flight> actual = getAllTestFlights();
        actual.removeAll(tempWrapper.arrivalBeforeDepartureFilter().applyFiltersAndReturnFilteredFlights());

        assertEquals(expected, actual);
    }

    @Test
    public void moreThanTwoHoursOnTheGroundTest() {
        List<Flight> expected = List.of(testFlight4, testFlight5);

        FlightListWrapper tempWrapper = new FlightListWrapper(getAllTestFlights());

        List<Flight> actual = getAllTestFlights();
        actual.removeAll(tempWrapper.moreThanTwoHoursOnTheGroundFilter().applyFiltersAndReturnFilteredFlights());

        assertEquals(expected, actual);
    }

    @Test
    public void departureInThePastAndMoreThanTwoHoursOnTheGroundTest() {
        List<Flight> expected = List.of(testFlight5);

        FlightListWrapper tempWrapper = new FlightListWrapper(getAllTestFlights());

        List<Flight> actual = getAllTestFlights();
        actual.removeAll(tempWrapper.departureInThePastFilter().moreThanTwoHoursOnTheGroundFilter()
                .applyFiltersAndReturnFilteredFlights());

        assertEquals(expected, actual);
    }
}
