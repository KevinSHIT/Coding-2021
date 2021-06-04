package Controllers;

public class Direction
{
    public static IFunc<Coordinate, Coordinate> CrossLeftUp = new IFunc<Coordinate, Coordinate>()
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() - 1, co.getY() + 1);
        }
    };

    public static IFunc<Coordinate, Coordinate> CrossLeftDown = new IFunc<Coordinate, Coordinate>()
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() - 1, co.getY() - 1);
        }
    };

    public static IFunc<Coordinate, Coordinate> CrossRightDown = new IFunc<Coordinate, Coordinate>()
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() + 1, co.getY() - 1);
        }
    };

    public static IFunc<Coordinate, Coordinate> CrossRightUp = new IFunc<Coordinate, Coordinate>()
    {
        @Override
        public Coordinate invoke(Coordinate co)
        {
            return new Coordinate(co.getX() + 1, co.getY() + 1);
        }
    };
}
