package edu.pdx.cs410J.jscott;

import org.junit.Test;

import javax.xml.soap.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by scottjones on 7/18/17.
 */
public class TextDumperTest {

    @Test
    public void givenAirlineDumpWritesToFile() {
        Airline southwest = new Airline("Southwest");
        Flight flight = new Flight("Southwest", 3130, "PDX",
                "09:00 12/30/2017", "LAX", "10:00 12/20/2017");
        southwest.addFlight(flight);
        TextDumper writer = new TextDumper("test.txt");

        try{
            writer.dump(southwest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))){
            while (br.ready()){
                //System.out.println(br.readLine());
                assertThat(br.readLine(), equalTo("Southwest"));
                assertThat(br.readLine(), equalTo("3130;PDX;09:00 12/30/2017;LAX;10:00 12/20/2017"));
            }
        } catch (IOException ex){
            System.err.println(ex);
        }

    }
}