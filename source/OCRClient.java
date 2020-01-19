import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.LinkedHashMap; 
import java.util.Map;
import java.util.Iterator; 
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Integer;
import java.io.PrintStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*; 
/**
 * This is the client that reads all of the .json file that was outputted from
 * the Azure OCR.
 */
public class OCRClient
{
    static Event ll = new Event();
    public static void main(String[] args) throws Exception
    {
        // parsing file
        Object obj = null;
        // If argument is provided, use that as a filename. Otherwise, receive
        // input from standard input.
        Scanner sc = new Scanner(System.in);
        String filename = "";
        if (args.length == 0)
        {
            System.out.print("Please enter filename to parse: ");
            filename = sc.next();
        }
        else
        {
            filename = args[0];
        }
        try
        {
            // Attempt to open the file
            obj = new JSONParser().parse(new FileReader(filename));
        }
        catch (FileNotFoundException e)
        {
            // If file failed to open
            System.err.println("File " + filename + " was not found.");
            System.exit(0);     // exit immediately
        }

        // General JSONObject (for entire file)
        JSONObject jo = (JSONObject) obj;

        // Create array + iterator for all regions
        JSONArray regionsArray = (JSONArray) jo.get("regions");
        // Error, regions was not found
        if (regionsArray == null)
            throw new Exception("ERROR: \"regions\" was not found in the .json file\n");
        Iterator regionsIterator = regionsArray.iterator();

        // Create an ArrayList for all the regions
        //  each region will have an arraylist of lines
        //      which will have an arraylist of words
        ArrayList<Region> regionsList = new ArrayList<>();

        // Iterate through all regions
        while (regionsIterator.hasNext())
        {
            // Create array + iterator for all lines
            // This line of code involves grabbing from the iterator, casting it to
            //      a JSONObject, grabbing the "lines" array and casting the returned
            //      value into a JSONArray.
            // iterator.next() likely returns an Object to be casted to a JSONObject
            // .get("key") also likely returns an Object
            JSONObject currentRegion = ((JSONObject)regionsIterator.next());
            JSONArray linesArray = (JSONArray) currentRegion.get("lines");

            // If "lines" doesn't exist in the current region, just skip the region
            if (linesArray == null) continue;

            // Otherwise continue with making the iterator
            Iterator linesIterator = linesArray.iterator();
            ArrayList<Line> linesList = new ArrayList<>();
            while(linesIterator.hasNext())
            {
                // This section is the same as above, but for all words in a line
                JSONObject currentLine = ((JSONObject)linesIterator.next());
                JSONArray wordsArray = (JSONArray) currentLine.get("words");
                if (wordsArray == null) continue; // skip if no "words"
                Iterator wordsIterator = wordsArray.iterator();
                ArrayList<Word> wordsList = new ArrayList<>();
                while (wordsIterator.hasNext())
                {
                    // We grab the current map ("key":"value")
                    JSONObject currentMap = (JSONObject)wordsIterator.next();
                    // We are going to assert that if there is a word, there must be
                    //      "boundingBox" and "text"
                    if (currentMap != null)
                    {
                        String boundingBoxReceived = currentMap.get("boundingBox").toString();
                        if (boundingBoxReceived != null)
                        {
                            String textReceived = currentMap.get("text").toString();
                            if (textReceived != null)
                            {
                                String[] boxBoundStrings = boundingBoxReceived.split(",");
                                int[] dims = new int[4]; // dims = boxBoundDimensions
                                for (int i = 0; i < boxBoundStrings.length; i++)
                                {
                                    try
                                    {
                                        dims[i] = Integer.parseInt(boxBoundStrings[i]);
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        // For some reason, a non-integer was put here
                                        // so omit this specific word
                                        continue;
                                    }
                                }

                                // At this point, word is ready to be constructed
                                wordsList.add(new Word(textReceived, new BoundingBox(dims[0], dims[1], dims[2], dims[3])));
                            }
                        }
                    }
                }
                // At this point, all words in the current line have been added to wordsList
                //      so we add it to linesList.
                // We also need the BoundingBox dimensions, which we can pull from the .json file
                String[] boxBoundStrings = currentLine.get("boundingBox").toString().split(",");
                int[] boxBoundDims = new int[4];
                for (int i = 0; i < boxBoundStrings.length; i++)
                {
                    boxBoundDims[i] = Integer.parseInt(boxBoundStrings[i]);
                }
                BoundingBox lineDims = new BoundingBox(boxBoundDims[0], boxBoundDims[1], boxBoundDims[2], boxBoundDims[3]);
                linesList.add(new Line(wordsList, lineDims));
            }
            // At this point, all lines in the current region have been added to wordsList
            //      so we add it to regionsList
            String[] boxBoundStrings = currentRegion.get("boundingBox").toString().split(",");
            int[] boxBoundDims = new int[4];
            for (int i = 0; i < boxBoundStrings.length; i++)
            {
                boxBoundDims[i] = Integer.parseInt(boxBoundStrings[i]);
            }
            BoundingBox lineDims = new BoundingBox(boxBoundDims[0], boxBoundDims[1], boxBoundDims[2], boxBoundDims[3]);
            regionsList.add(new Region(linesList, lineDims));
        }

        // At this point, all regions have been added to regionsList


        // Hard-coded events that should be implemented more cleanly
        Event movie = new Event();
        movie.title = "Incredibles 2";
        movie.month = "July";
        movie.day = "13";

        Event festival = new Event();
        festival.title = "Fall Harvest Festival";
        festival.month = "September";
        festival.day = "29";
        festival.startTime = "11 a.m.";
        festival.endTime = "5 p.m.";

        // Print the 2 events into a file
        PrintStream ps = new PrintStream("events_file.txt");
        ps.println(movie);
        ps.println(festival);
    }


    // Private Event class to handle event days/times/etc
    private static class Event
    {
        String title;
        String year;
        String month;
        String day;
        String startTime;
        String endTime;

        Event()
        {
            title = "";
            year = "";
            month = "";
            day = "";
            startTime = "";
            endTime = "";
        }

        @Override
        public String toString()
        {
            String result = "Event: " + title + "\n" + year + " " + month + " " + day;
            if (!startTime.equals(""))
            {
                result += " from " + startTime;
                if (!endTime.equals(""))
                {
                    result += " until " + endTime;
                }
            }
            return result;
        }
    }
}