package Game;

/**
 * Represent the opposite player.
 * If want to reverse player, we can just use minus to represent.
 */
public class Player
{
    public final static int RED = 1;
    public final static int BLACK = -1;

    public static String toString(int id)
    {
        switch (id)
        {
            case -1:
                return "Black";
            case 1:
                return "Red";
            default:
                return "Unknown";
        }
    }
}
