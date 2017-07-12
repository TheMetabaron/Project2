package edu.pdx.cs410J.jscott;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

/**
 * Created by scottjones on 7/9/17.
 */
public class AirlineTest {
    @Test
    public void getNameMatchesInput() throws Exception {
        Airline united = new Airline("United");
        assertThat(united.getName(), equalTo("United"));
    }

    @Test
    public void addFlightisRetreivedByGetFlight() throws Exception {
        Airline southwest = new Airline("Southwest");
        Flight flight = new Flight("Southwest", 666, "PDX", "08:00", "LAX", "11:00");

        southwest.addFlight(flight);
        assertTrue(southwest.getFlights().contains(flight));

    }


}