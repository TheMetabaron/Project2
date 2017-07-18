package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import java.io.IOException;


/**
 * The main class for the CS410J airline Project2. The main method parses the command line input which is expected
 * to be in the order name, flightNumber, src, departTime, dest, arriveTime. If the input is appropriate, a new airline
 * object is created and a new flight object is created and added to the airline object. This class also contains methods
 * to handle command line flags for the README file and to print the new data back to the console.
 *
 * Project2 adds the functionality for -textFile file
 */
public class Project2 {

    public static void main(String[] args) {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

        if(args.length > 10) {
            System.err.println("Error: Unable to enter flight info due to incorrect number of arguments. " +
                    "Expected: name, flightNumber, src, departTime, dest, arriveTime");
            System.exit(1);
        }
        String [] commands = new String[8];
        String [] flags = new String[4];
        int i = 0;
        for (int j = 0; i < args.length; ++j){
            //Check for flags
            if((args[j]).charAt(0) == '-'){
                if((args[j]).equalsIgnoreCase("-print")){
                    flags[0] = (args[j]);
                    //call print description of the new flight
                }
                if((args[j]).equalsIgnoreCase("-README")){
                    flags[1] = (args[j]);
                    //call print a README for this project and exit
                    printReadMe();
                }
                if((args[j]).equalsIgnoreCase("-textFile")){
                    flags[2] = (args[j]);
                    //Make sure filename is included after textfile
                    if(j+1 < args.length){
                        ++j;
                        flags[3] = args[j];
                    }
                    else{
                        System.err.println("Error: Expects argument after -textFile");
                        System.exit(1);
                    }

                }
            }
            else {
                commands[i] = args[j];
                ++i;
            }
        }

        if(i < 8) {
            System.err.println("Error: Missing command line arguments. Expected: name, flightNumber, src, departTime, dest, arriveTime");
            System.exit(1);

        }
        //Convert FlightValue to int
        int flightValue = 0;
        try{
            flightValue = Integer.parseInt(commands[1]);
        }
        catch(NumberFormatException ex){
            System.err.println("Second argument must be an integer: " + commands[1]);
            System.exit(1);
        }
        //Check command line arguments
        checkCommandLineArguments(commands);

        Airline airline = new Airline(commands[0]);
        Flight flight = new Flight(commands[0], flightValue, commands[2], commands[3] + " " + commands[4],
                commands[5], commands[6] + " " +commands[7]);
        airline.addFlight(flight);

        //-print flag prints description of the new flight
        if(flags[0] != null) {
            System.out.println(flight.toString());
        }
        System.exit(0);

        //-textFile flag prints airline to file
        if(flags[2] != null){
            TextDumper textDumper = new TextDumper(flags[3]);
            try {
                textDumper.dump(airline);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method prints an explanation of the program functionality.
     */
    private static void printReadMe() {
        System.out.println("***********************************************\n" +
                "* README FOR PROJECT TWO: AIRLINE APPLICATION *\n" +
                "***********************************************\n");
        System.out.println("This program was written by Scott Jones for CS510J\n");
        System.out.println("The purpose of this program is to simulate an Airline booking application.\n" +
                "The current functionality includes the ability to enter the information for\n" +
                "a flight at the command line and that information will be entered into the\n" +
                "fundamental Airline and Flight objects\n");
        System.out.println("USAGE\n\n" +
                "java edu.pdx.cs410J.jscott.Project1 [options] <args>\n\n" +
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
                "-print\t\t\tPrints a description of the new flight\n" +
                "-README\t\t\tPrints a README for this project and exits\n");
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
        if(!commands[3].matches("([1-9]|(1[0-2]))/([0-9]|[1-2][0-9]|30)/([0-9]{4})")
                | !commands[4].matches("(([0-1][0-9])|(2[0-4])):(([0-4][0-9])|5[0-9])")
                | !commands[6].matches("([1-9]|(1[0-2]))/([0-9]|[1-2][0-9]|30)/([0-9]{4})")
                | !commands[7].matches("(([0-1][0-9])|(2[0-4])):([0-5][0-9])")){
            System.err.println("Error: 4th and 6th arguments must be in the format mm/dd/yyyy hh:mm");
            System.err.println("Your entries were: " + commands[3] + " " + commands[4]
                    + " and " + commands[6] + " " + commands[7]);
            System.exit(2);
        }
    }

}
