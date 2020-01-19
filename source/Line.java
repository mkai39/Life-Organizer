import java.util.ArrayList;

public class Line
{
    public ArrayList<Word> words;
    public BoundingBox box;
    public Line(ArrayList<Word> words, BoundingBox box)
    {
        this.words = words;
        this.box = box;
    }

    @Override
    public String toString()
    {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("line: ");
        for (int i = 0; i < words.size(); i++)
        {
            lines.add("\n\t\t" + words.get(i));
        }
        String result = "";
        for (int i = 0; i < lines.size(); i++)
        {
            result += lines.get(i).toString();
        }
        return result;
    }
}