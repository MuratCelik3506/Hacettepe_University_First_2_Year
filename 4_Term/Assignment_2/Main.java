import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//********************************************************************
//******************* Murat Celik ************************************
//*******************  21827263   ************************************
//********************************************************************

public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static void main(String[] args) throws IOException {
        String file_name = args[0];   // take a json file from bash
        Assignment[] input_array = readFile(file_name);  // read json and save as array

        Arrays.sort(input_array);  //  sort according to finish time
        Scheduler scheduler = new Scheduler(input_array); // create Scheduler object
        ArrayList<Assignment> list_dynamic = scheduler.scheduleDynamic();
        ArrayList<Assignment> list_greedy = scheduler.scheduleGreedy();


        writeOutput("solution_dynamic.json",list_dynamic); 
        writeOutput("solution_greedy.json",list_greedy);

    }

    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    // Read json file use Gson library
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson(); // create Gson object
        Assignment[] staff = new Assignment[0];
        Reader reader = new FileReader(filename);
            staff = gson.fromJson(reader, Assignment[].class); // this method convert json file to array format which you choose
        
        return staff;
    }

    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    // Write json file use Gson library
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create(); // some charachter wrote binary format, but this addition method save us
            gson.toJson(arrayList, writer);  //this method convert array to json file
            // (add note : it is very useful library)
        }
    }
}
