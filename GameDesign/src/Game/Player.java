package Controllers;

/**
 * 表示对立两方
 * 认为两方分别为1和-1，如果需要反转只需要进行-选项即可
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
