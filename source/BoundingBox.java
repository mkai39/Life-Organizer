
// BoundingBox class is used to hold information about
//      the rectangular bounds that Azure's OCR system
//      uses to indicate where words are in an image
public class BoundingBox
{
    // Bounding boxes as a result of the output of the json file
    // contain 4 fields:
    //      leftCoord  = how far box is from the left side of image
    //      topCoord   = how far box is from the top of image
    //      width       = how wide (horizontally) box is
    //      height      = how tall (vertically) box is
    // The json files tend to only return whole numbers, so the types
    //      of these fields will be int
    
    // Fields
    public int leftCoord;
    public int topCoord;
    public int width;
    public int height;

    // Constructors
    public BoundingBox(int l, int t, int w, int h)
    {
        leftCoord = l;
        topCoord = t;
        width = w;
        height = h;
    }
    public BoundingBox()
    {
        this(0, 0, 0, 0);
    }

    @Override
    public String toString()
    {
        return "boundingBox: " + leftCoord + ", " + topCoord + ", " + width + ", " + height;
    }
}