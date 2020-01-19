public class Word
{
    public String text;
    public BoundingBox box;
    
    public Word(String text, BoundingBox box)
    {
        this.text = text;
        this.box = box;
    }

    @Override
    public String toString()
    {
        return "text: " + "\"" + text + "\"" + "; " + box;
    }
}