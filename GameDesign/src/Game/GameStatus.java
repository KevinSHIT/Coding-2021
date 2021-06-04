package Game;

public enum GameStatus
{
    RedWin("Red Win"),
    BlackWin("Black Win"),
    Draw("Draw"),
    Gaming("Gaming");

    private final String statusStr;

    GameStatus(String s)
    {
        statusStr = s;
    }

    @Override
    public String toString()
    {
        return statusStr;
    }
}
