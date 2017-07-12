package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractFlight;

/**
 * The Flight class defines an object that represents all the necessary data fields for a flight. This includes the
 * flight number, airline name, 3 digit source airport code, departure time, 3 digit destination airport code, and
 * arrival time. The time fields should be formatted prior to input in the form dd/mm/yyyy hh:mm on a 24 hour clock.
 */
public class Flight extends AbstractFlight {

  private int FlightNumber;
  private String Name;
  private String Src;
  private String DepartTime;
  private String Dest;
  private String ArriveTime;

  /**
   * The default constructor does not take any arguments and sets default dummy values for each field.
   */
  public Flight(){
    super();
    Name = "Default";
    FlightNumber = 42;
    Src = "ABC";
    DepartTime = "00:00";
    Dest = "ABC";
    ArriveTime = "00:00";
  }

  /**
   * A constructor with parameters for each data field.
   * @param AirlineName
   * @param FlightValue
   * @param SourceAirportCode
   * @param DepartureTime
   * @param DestinationCode
   * @param ArrivalTime
   */
  public Flight (String AirlineName, int FlightValue, String SourceAirportCode, String DepartureTime, String DestinationCode, String ArrivalTime){
    super();
    Name = AirlineName;
    FlightNumber = FlightValue;
    Src = SourceAirportCode;
    DepartTime = DepartureTime;
    Dest =  DestinationCode;
    ArriveTime = ArrivalTime;
  }

  /**
   * A method to get the name of the airline.
   * @return  the name of the airline
   */
  public String getName(){
    return Name;
  }

  /**
   * A method to return the flight number.
   * @return  Flight number
   */
  @Override
  public int getNumber() {
    return FlightNumber;
  }

  /**
   * A method to return the 3 digit source airport code
   * @return  An airport code
   */
  @Override
  public String getSource() {
    return Src;
  }

  /**
   * A method without parameters that returns the departure date and time as a single string
   * @return  the departure time
   */
  @Override
  public String getDepartureString() {return DepartTime;}

  /**
   * A getter for the destination code
   * @return  destination code
   */
  @Override
  public String getDestination() {
    return Dest;
  }

  /**
   * A getter for the arrival date and time as a single string
   * @return  arrival date and time
   */
  @Override
  public String getArrivalString() {
    return ArriveTime;
  }
}
