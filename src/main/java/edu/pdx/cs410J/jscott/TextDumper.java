package edu.pdx.cs410J.jscott;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;


public class TextDumper implements AirlineDumper {

    private String filename;

    public TextDumper(){
        filename = "default";
    }

    public TextDumper(String file){
        filename = file;
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        PrintWriter err;
        err = new PrintWriter(System.err, true);

        try {
            Writer writer = new FileWriter(filename);

            //Write Airline to the file
            writer.write(airline.getName() + "\n");
            ArrayList <Flight> flights = new ArrayList<>(airline.getFlights());
            for (Flight f : flights) {
                writer.write(f.getNumber() + ";");
                writer.write(f.getSource() + ";");
                writer.write(f.getDepartureString() + ";");
                writer.write(f.getDestination() + ";");
                writer.write(f.getArrivalString() + "\n");
            }


            //All done
            writer.flush();
            writer.close();
        } catch(IOException ex){
            throw new IOException(ex);
        }
    }
}