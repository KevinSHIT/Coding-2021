package Game;

public enum GameStatus
{
    RedWin("Red Won"),
    BlackWin("Black Won"),
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
