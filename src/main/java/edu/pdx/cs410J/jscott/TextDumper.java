package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * The TextDumper class implements the Airline Dumper interface. A TextDumper dumps the contents of an airline including
 * it's flights, to a text file. Constructor is overloaded to take a filename.
 */
public class TextDumper implements AirlineDumper {

    private String filename;

    /**
     * Constructor
     */
    public TextDumper(){
        filename = "default";
    }

    /**
     * Constructor
     * @param file file/pathname to be dumped to
     */
    public TextDumper(String file){
        filename = file;
    }

    /**
     * The dump method provides the main functionaility for the TextDumper class. This method  opens the file name
     * passed to the constructor and adds the new flight to the file.
     * @param airline
     * @throws IOException
     */
    @Override
    public void dump(AbstractAirline airline) throws IOException {
        PrintWriter err;
        err = new PrintWriter(System.err, true);

        try {
            Writer writer = new FileWriter(filename);

            //Write Airline to the file
            writer.append(airline.getName());
            writer.append("\n");
            ArrayList <Flight> flights = new ArrayList<>(airline.getFlights());
            for (Flight f : flights) {
                writer.append(f.getNumber() + ";");
                writer.append(f.getSource() + ";");
                writer.append(f.getDepartureString() + ";");
                writer.append(f.getDestination() + ";");
                writer.append(f.getArrivalString() + "\n");
            }


            //All done
            writer.flush();
            writer.close();
        } catch(IOException ex){
            throw new IOException(ex);
        }
    }
}