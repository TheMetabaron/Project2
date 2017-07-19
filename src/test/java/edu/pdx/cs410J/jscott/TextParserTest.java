package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by scottjones on 7/18/17.
 */
public class TextParserTest {

    @Test
    public void textParserCreatesAirlineEqualtoTextDumper(){
        Airline southwest = new Airline("Southwest");
        Flight flight = new Flight("Southwest", 3130, "PDX",
                "09:00 12/30/2017", "LAX", "10:00 12/20/2017");
        southwest.addFlight(flight);
        TextDumper writer = new TextDumper("testParser.txt");
        try{
            writer.dump(southwest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextParser parser = new TextParser("testParser.Txt");
        Airline testAirline = null;
        try {
            testAirline = new Airline(parser.parse());
        } catch (ParserException e) {
            e.printStackTrace();
        }
        assertThat(southwest.getName(), equalTo(testAirline.getName()));
        //assertThat(southwest.getFlights(), equalTo(testAirline.getFlights()));
    }

}