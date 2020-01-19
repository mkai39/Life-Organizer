import java.util.ArrayList;

public class Region
{
    public ArrayList<Line> lines;
    public BoundingBox box;
    public Region(ArrayList<Line> lines, BoundingBox box)
    {
        this.lines = lines;
        this.box = box;
    }

    @Override
    public String toString()
    {
        ArrayList<String> regions = new ArrayList<>();
        regions.add("region: " + box);
        for (int i = 0; i < lines.size(); i++)
        {
            regions.add("\n\t" + lines.get(i));
        }
        String result = "";
        for (int i = 0; i < regions.size(); i++)
        {
            result += regions.get(i).toString();
        }
        return result;
    }
}