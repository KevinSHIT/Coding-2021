package Game;

/**
 * 坐标类，用于记录x和y值
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
