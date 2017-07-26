package edu.pdx.cs410J.jscott;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight();
    assertThat(flight.getNumber(), equalTo(42));
  }

  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight();
    assertThat(flight.getDeparture(), is(nullValue()));
  }
/*
  @Test
  public void doesflightNumberMatchGetNumberMethod() {
    int FlightNumber = 666;
    Flight flight = new Flight("Southwest", FlightNumber, "PDX", "08:00", "SFO", "10:00");
    assertThat(flight.getNumber(), equalTo(FlightNumber));
  }

  @Test
  public void constructorValueForSourceMatchesField(){
    Flight flight = new Flight ("Southwest", 123, "PDX", "08:00", "SFO", "10:00");
    assertThat(flight.getSource(), equalTo("PDX"));
  }

  @Test
  public void constructorValueForDepartureStringMatchesField(){
    Flight flight = new Flight ("Southwest", 123, "PDX", "08:00", "SFO", "10:00");
    assertThat(flight.getDepartureString(), equalTo("08:00"));
  }

  @Test
  public void constructorValueForDestMatchesField(){
    Flight flight = new Flight ("Southwest", 123, "PDX", "08:00", "SFO", "10:00");
    assertThat(flight.getDestination(), equalTo("SFO"));
  }

  @Test
  public void constructorValueForArriveTimeMatchesField(){
    Flight flight = new Flight ("Southwest", 123, "PDX", "08:00", "SFO", "10:00");
    assertThat(flight.getArrivalString(), equalTo("10:00"));
  }*/
}
