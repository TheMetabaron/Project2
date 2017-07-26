package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by scottjones on 7/26/17.
 */
public class PrettyPrinter implements AirlineDumper {

    private String filename;

    /**
     * Default constructor sets filename to console which triggers print to console
     */
    public PrettyPrinter() {
        filename = "-";
    }

    /**
     * Constructor for creating a pretty printer to print to file.
     *
     * @param fileName the name of the text file to be printed to
     */
    public PrettyPrinter(String fileName) {
        filename = fileName;
    }

    /**
     * Implementation of dump method for the PrettyPrinter class. If argument is "-", prints to console
     * Otherwise prints to file speified by argument.
     *
     * @param airline The filename for output. If filename is "-" prints to console
     * @throws IOException
     */
    @Override
    public void dump(AbstractAirline airline) throws IOException {

        if (filename.equalsIgnoreCase("-")) {
            //If filename == "-" -> print to console

        }
        //print to filename
        else{
            PrintWriter err;
            err = new PrintWriter(System.err, true);

            try {
                Writer writer = new FileWriter(filename);

                //Write Airline to the file
                writer.append("*************************************************\n");
                writer.append("Schedule of Flights for: ");
                writer.append(airline.getName() + "\n");
                writer.append("*************************************************\n");
                ArrayList<Flight> flights = new ArrayList<>(airline.getFlights());
                for (Flight f : flights) {
                    writer.append("Flight Number:       ");
                    writer.append(f.getNumber() + "\n");
                    writer.append("Departure Airport:   ");
                    writer.append(AirportNames.getName(f.getSource()) + "\n");
                    writer.append("Departure Date:      ");
                    writer.append(f.getDeparture().toString() + "\n");
                    writer.append("Destination Airport: ");
                    writer.append(AirportNames.getName(f.getDestination()) + "\n");
                    writer.append("Arrival Date:        ");
                    writer.append(f.getArrival().toString() + "\n");
                    writer.append("-------------------------------------------------\n");
                }
                //All done
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                throw new IOException(ex);
            }
        }
    }
}
