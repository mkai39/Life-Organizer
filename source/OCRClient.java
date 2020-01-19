import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.LinkedHashMap; 
import java.util.Map;
import java.util.Iterator; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*; 
/**
 * This is the client that reads all of the .json file that was outputted from
 * the Azure OCR.
 */
public class OCRClient
{
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
    {
        // parsing file "JSONExample.json"
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(new FileReader(args[0]));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File " + args[0] + " was not found.");
            System.exit(1);     // exit immediately
        }
        JSONObject jo = (JSONObject) obj;

        JSONArray regionsArray = (JSONArray) jo.get("regions");
        Iterator regionsIterator = regionsArray.iterator();
        while (regionsIterator.hasNext())
        {
            JSONArray linesArray = (JSONArray) ((JSONObject)regionsIterator.next()).get("lines");
            Iterator linesIterator = linesArray.iterator();
            while(linesIterator.hasNext())
            {
                JSONArray wordsArray = (JSONArray) ((JSONObject)linesIterator.next()).get("words");
                Iterator wordsIterator = wordsArray.iterator();
                while (wordsIterator.hasNext())
                {
                    JSONObject currentMap = (JSONObject)wordsIterator.next();
                    System.out.println(currentMap.get("text"));
                    //Map.Entry pair = (Map.Entry)currentMap;
                    //System.out.println(pair.getKey() + ":" + pair.getValue());
                }
            }
        }
    }
}