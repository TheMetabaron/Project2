package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


/**
 * The main class for the CS410J airline Project3. The main method parses the command line input which is expected
 * to be in the order name, flightNumber, src, departTime, dest, arriveTime. If the input is appropriate, a new airline
 * object is created and a new flight object is created and added to the airline object. This class also contains methods
 * to handle command line flags for the README file and to print the new data back to the console.
 *
 * Project2 adds the functionality for -textFile file
 */
public class Project3 {

    public static void main(String[] args) {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

        if (args.length > 15) {
            System.err.println("Error: Maximum number of arguments exceeded. Unable to enter flight info due to incorrect" +
                    " number of arguments. Expected: name, flightNumber, src, departTime, dest, arriveTime");
            System.exit(1);
        }
        String[] commands = new String[10];
        String[] flags = new String[6];
        int i = 0;
        for (int j = 0; j < args.length; ++j) {
            //Check for flags
            if (args[j].charAt(0) == '-') {
                if ((args[j]).equalsIgnoreCase("-print")) {
                    flags[0] = (args[j]);
                    //call print description of the new flight
                }
                if ((args[j]).equalsIgnoreCase("-README")) {
                    flags[1] = (args[j]);
                    //call print a README for this project and exit
                    printReadMe();
                }
                if ((args[j]).equalsIgnoreCase("-textFile")) {
                    flags[2] = (args[j]);
                    //Make sure filename is included after text file flag
                    if (j + 1 < args.length) {
                        ++j;
                        flags[3] = args[j];
                    } else {
                        System.err.println("Error: Expects argument after -textFile");
                        System.exit(1);
                    }
                }
                if ((args[j]).equalsIgnoreCase("-pretty")) {
                    flags[4] = (args[j]);
                    //make sure file name is included
                    if (j + 1 < args.length) {
                        ++j;
                        flags[4] = args[j];
                    } else {
                        System.err.println("Error: Expects argument after -pretty");
                        System.exit(1);
                    }
                } else {
                    if (i > 7) {
                        System.err.println("Error: To many command line arguments. Expected: name, flightNumber, src, departTime, dest, arriveTime");
                        System.exit(1);
                    }
                    commands[i] = args[j];
                    ++i;
                }
            }
            if (i < 8) {
                System.err.println("Error: Missing command line arguments. Expected: name, flightNumber, src, departTime, dest, arriveTime");
                System.exit(1);
            }

            //Convert FlightValue to int
            int flightValue = 0;
            try {
                flightValue = Integer.parseInt(commands[1]);
            } catch (NumberFormatException ex) {
                System.err.println("Second argument must be an integer: " + commands[1]);
                System.exit(1);
            }
            //Check command line arguments
            checkCommandLineArguments(commands);

            //parse date objects
            DateTimeParser dateTime = new DateTimeParser();
            Date arrivalTime = dateTime.parse(commands[3] + " " + commands[4]);
            Date departureTime = dateTime.parse(commands[6] + " " + commands[7]);


            //if -printFile flag load Airline from file else create new
            Airline airline;
            if (flags[2] != null && flags[3] != null) {
                TextParser parser = new TextParser(flags[3]);
                try {
                    File f = new File(flags[3]);
                    if (f.isFile()) {

                        airline = new Airline(parser.parse());

                        //verify airline name from file matches command line airline if not exit
                        if (!airline.getName().equalsIgnoreCase(commands[0])) {
                            System.err.println("Error: Unable to add new flight because the airline " + commands[0]
                                    + " you entered does not match the airline " + airline.getName() + " stored in file"
                                    + flags[3]);
                            System.exit(1);
                        }
                    } else {
                        //if file does not exist create new empty airline
                        airline = new Airline(commands[0]);
                    }
                } catch (ParserException ex) {
                    System.err.println("Unable to open requested file " + flags[3] + ". Creating new file.");
                    airline = new Airline(commands[0]);
                }
            } else {
                airline = new Airline(commands[0]);
            }

            //Create new Flight
            Flight flight = new Flight(commands[0], flightValue, commands[2], departureTime,
                    commands[5], arrivalTime);
            airline.addFlight(flight);

            //-print flag prints description of the new flight
            if (flags[0] != null) {
                System.out.println(flight.toString());
            }

            //-textFile flag prints airline to file
            if (flags[2] != null && flags[3] != null) {
                TextDumper textDumper = new TextDumper(flags[3]);
                try {
                    textDumper.dump(airline);
                } catch (IOException e) {
                    System.err.println("Text dumper failed due to IO Exception");
                    e.printStackTrace();
                }
            } else if (flags[2] != null) {
                System.err.println("Error: no filename indicated");
            }
            System.exit(0);
        }
    }

    /**
     * This method prints an explanation of the program functionality.
     */
    private static void printReadMe() {
        System.out.println("***********************************************\n" +
                "* README FOR PROJECT THREE: AIRLINE APPLICATION *\n" +
                "***********************************************\n");
        System.out.println("This program was written by Scott Jones for CS510J\n");
        System.out.println("The purpose of this program is to simulate an Airline booking application.\n" +
                "The current functionality includes the ability to enter the information for\n" +
                "a flight at the command line and that information will be entered into the\n" +
                "fundamental Airline and Flight objects.\n" +
                "Added for project 2 is the printFile flag that reads and prints to file\n." +
                "Added for project 3 is the pretty flag that prints an Airline's flights to a text file or standard out.\n");
        System.out.println("USAGE\n\n" +
                "java edu.pdx.cs410J.jscott.Project2 [options] <args>\n\n" +
                "Command Line Arguments:\n" +
                "This program expects the following arguments in the order listed\n" +
                "name\t\t\tThe name of the airline\n" +
                "flightNumber\tThe flight number\n" +
                "src\t\t\t\tThree letter code of the departure airport\n" +
                "departTime\t\tTThe departure date and time\n" +
                "dest\t\t\tThree letter code of the arrival airport\n" +
                "arriveTime\t\tArrival date and time (24 hour time see note)\n");
        System.out.println("note: Date and time should be in the format: mm/dd/yyyy hh:mm");
        System.out.println("\nOptions:\n" +
                "-textFile file\t\tWhere to read/write the airline info\n" +
                "-print\t\t\t\tPrints a description of the new flight\n" +
                "-README\t\t\t\tPrints a README for this project and exits\n");
        System.exit(4);
    }

    /**
     * This method is called by main in order to verify the commands are in the appropriate format.
     * An error message is printed ot screen if an error is found.
     * @param commands  a string array containing all command line arguments
     */
    private static void checkCommandLineArguments(String [] commands){
        //check to make sure airport code is 3 letter (2 and 4)
        if(!commands[2].matches("[a-zA-z]{3}") || !commands[5].matches("[a-zA-z]{3}")){
            System.err.println("Error: The airport codes must consist of three letters");
            System.err.println("Your entries were: " + commands[2] + " and " + commands[5]);
            System.exit(2);
        }

        //check DepartTime and ArriveTime are in format mm/dd/yyyy hh:mm  (3 and 5)
        /*if(!commands[3].matches("(0?[1-9]|(1[0-2]))/([0-9]|[0-2][0-9]|3[0-1])/([0-9]{4})")
                | !commands[4].matches("(([0-1][0-9])|(2[0-4])):(([0-4][0-9])|5[0-9])")
                | !commands[6].matches("(0?[1-9]|([0-1][0-2]))/([0-9]|[0-2][0-9]|3[0-1])/([0-9]{4})")
                | !commands[7].matches("(([0-1][0-9])|(2[0-4])):([0-5][0-9])")){
            System.err.println("Error: 4th and 6th arguments must be in the format mm/dd/yyyy hh:mm");
            System.err.println("Your entries were: " + commands[3] + " " + commands[4]
                    + " and " + commands[6] + " " + commands[7]);
            System.exit(2);
        }*/
    }

}
