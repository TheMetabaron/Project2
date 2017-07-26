package edu.pdx.cs410J.jscott;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
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
        Date date1 = new Date(123456);
        Date date2 = new Date(123999);
        Calendar cal = Calendar.getInstance();
        cal.set(2017, 12, 30, 8, 00);


        Flight flight = new Flight("Southwest", 666, "PDX", date1, "LAX", date2);

        southwest.addFlight(flight);
        assertTrue(southwest.getFlights().contains(flight));

    }


}