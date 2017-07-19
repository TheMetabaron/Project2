package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Airline Class was written for project 1 in order to represent an airline object and all flights associated with
 * that Airline. Each Airline contans a name field and a list of flights. The Airline class contains method
 * implementations to override the abstraxt parent class AbstractAirline.
 */
public class Airline extends AbstractAirline{

    private String name;
    private List<AbstractFlight> flightList;

    /**
     * Constructor takes the airline name as an argument and generates an empty list of flights. Must use the addFlight()
     * method to add flights to the airline object.
     * @param airlineName
     */
    public Airline (String airlineName){
      name = airlineName;
      flightList = new ArrayList<>();
    }

    //Default Constructor
    public Airline(){
    }

    /**
     * Copy Constructor
     * @param airline
     */
    //Copy constructor
    public Airline (AbstractAirline airline){
        name = airline.getName();
        flightList = new ArrayList<>(airline.getFlights());
    }

    /**
     * Method that returns the name of the airline.
     * @return  The name of the airline
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to add a flight to the list of flights for an airline object. Takes an Flight object as an argument.
     * @param abstractFlight
     */
    @Override
    public void addFlight(AbstractFlight abstractFlight) {
        flightList.add(abstractFlight);
    }

    /**
     * Method that retuns a Collection object representing the list of flights.
     * @return  a collection object that is a list of flight objects.
     */
    @Override
    public Collection getFlights() {
        return flightList;
    }
}
