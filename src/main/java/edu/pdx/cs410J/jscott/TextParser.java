package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by scottjones on 7/18/17.
 */
public class TextParser implements AirlineParser {

    private static Airline airline;
    private static String filename;

    public TextParser(){
        filename = "default.txt";
    }

    public TextParser(String file){
        filename = file;
    }

    // Throws Exception when FileReader generated IO exception
    // Returns new Airline if textfile does not exist
    @Override
    public AbstractAirline parse() throws ParserException {
        String airlineName;
        String [] args = new String [5];

        //if parse previously run, return error.
        if(airline != null){
            System.err.println("Error: This TextParser was previously run");
            return null;
        }

        // Check to make sure the file exists
        File f = new File(filename);
        if(f.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                if (br.ready()) {
                    //First gets Airline Name
                    airlineName = br.readLine();
                    //was file empty?
                    if(airlineName.isEmpty()){
                        System.err.println("Error while parsing file: " + filename + ". The file was empty or formatted incorrectly");
                        System.exit(1);
                    }
                    airline = new Airline(airlineName);

                    while (br.ready()) {
                        //Each Subsequent line should be data for a single flight (5 data points)
                        StringTokenizer parser = new StringTokenizer(br.readLine(), ";");
                        if (parser.countTokens() != 5) {
                            System.err.println("ERROR: While parsing file did not get expected 5 arguments for a flight.\n" +
                                    "Make Sure the File: " + filename + "is formatted correctly.");
                            System.exit(1);
                        }
                        int i = 0;
                        while (parser.hasMoreTokens()) {
                            args[i] = parser.nextToken();
                            i++;
                        }
                        Flight flight = new Flight(airlineName, Integer.valueOf(args[0]),
                                args[1], args[2], args[3], args[4]);
                        airline.addFlight(flight);
                    }
                }
            } catch (IOException ex) {
                System.err.println("Error reading file");
                throw new ParserException("While parsing text file ", ex);
            }
        }
        else{
            System.err.println("Error reading file. File name not found");
            System.exit(1);
        }

        if(airline == null){
            System.err.println("Error reading file. File is not formatted correctly");
            System.exit(1);
        }
        return airline;
    }
}
