package Game;

/**
 * Coordinate, for record x and y
 */
public class Coordinate
{
    int x, y;

    public Coordinate(int xIn, int yIn)
    {
        x = xIn;
        y = yIn;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }
}
