package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board
{
    public final static int WIDTH = 7;
    public final static int HEIGHT = 6;
    int[][] grid;
    GameStatus status;
    int current;

    //region getter
    public GameStatus getGameStatus()
    {
        return status;
    }

    public int[][] getGrid()
    {
        return grid;
    }

    public int getCurrentPlayer()
    {
        return current;
    }
    //endregion

    public Board()
    {
        restartGame();
    }

    public void restartGame()
    {
        grid = new int[WIDTH][HEIGHT];
        status = GameStatus.Gaming;
        current = Player.RED;
    }


    public void updateGameStatus(int xIn)
    {
        int yIn = 0;
        // 获取最上层的坐标
        for (int y = HEIGHT - 1; y >= 0; --y)
        {
            if (grid[xIn][y] !=0)
            {
                yIn = y;
                break;
            }
        }

        // Vertical
        if (updatePlayerStatus(isWin(grid[xIn])))
            return;

        // Horizontal
        List<Integer> col = new ArrayList<>();
        for (int x = 0; x < WIDTH; ++x)
        {
            col.add(grid[x][yIn]);
        }
        System.out.println(Arrays.toString(convertToIntArray(col)));
        if (updatePlayerStatus(isWin(convertToIntArray(col))))
            return;

        // Cross
        List<int[]> l = new ArrayList<>();
        // Left corner
        int min = Math.min(xIn, yIn);
        l.add(getLines(new Coordinate(xIn - min, yIn - min), Direction.CrossRightUp));
        // Right Corner
        int nx = 6 - xIn;
        min = Math.min(nx, yIn);
        l.add(getLines(new Coordinate(xIn + min, yIn - min), Direction.CrossLeftUp));

        for (int[] value : l)
        {
            if (updatePlayerStatus(isWin(value)))
                return;
        }

        if (isFull())
        {
            status = GameStatus.Draw;
        }
    }

    private int[] getLines(Coordinate start, IFunc<Coordinate, Coordinate> direct)
    {
        List<Integer> l = new ArrayList<>();
        l.add(grid[start.getX()][start.getY()]);
        while (true)
        {
            start = direct.invoke(start);
            if (!isInside(start.getX(), start.getY()))
                break;
            l.add(grid[start.getX()][start.getY()]);
        }
        return convertToIntArray(l);
    }

    public int[] convertToIntArray(List<Integer> l)
    {
        //(Integer[])l.toArray()) is not int[]
        int[] array = new int[l.size()];
        for (int i = 0; i < l.size(); ++i)
        {
            array[i] = l.get(i);
        }
        return array;
    }

    public boolean updatePlayerStatus(int player)
    {
        switch (player)
        {
            case 1:
                status = GameStatus.RedWin;
                return true;
            case -1:
                status = GameStatus.BlackWin;
                return true;
        }
        return false;
    }

    private int isWin(int[] lines)
    {
        if (lines.length < 4)
            return 0;

        // MID POINT OPTIMISATION
        if (lines.length < 8)
        {
            int mid = lines.length / 2;
            int midPlayer = lines[mid];

            if (midPlayer == 0)
                return 0;

            int index = mid;
            int count = 1;
            while (true)
            {
                --index;
                if (index < 0)
                    break;

                if (lines[index] == midPlayer)
                    ++count;
                else
                    break;
            }

            index = mid;
            while (true)
            {
                ++index;
                if (index >= lines.length)
                    break;

                if (lines[index] == midPlayer)
                    ++count;
                else
                    break;
            }

            if (count >= 4)
                return midPlayer;
            else
                return 0;
        }

        int maxCount = 0;
        int maxPlayer = 0;
        int prev = 0;
        int count = 0;
        for (int value : lines)
        {
            if (value == 0)
            {
                if (count > maxCount)
                {
                    maxCount = count;
                    maxPlayer = prev;
                }
                prev = 0;
                count = 0;
                continue;
            }

            if (prev == value)
            {
                ++count;
                if (count > maxCount)
                {
                    maxCount = count;
                    maxPlayer = prev;
                }
                continue;
            }

            if (count > maxCount)
            {
                maxCount = count;
                maxPlayer = prev;
            }
            prev = value;
            count = 1;
        }

        if (maxCount < 4)
            return 0;
        return maxPlayer;
    }

    public boolean isFull()
    {
        for (int x = 0; x < WIDTH; ++x)
        {
            for (int y = 0; y < HEIGHT; ++y)
            {
                if (grid[x][y] == 0)
                    return false;
            }
        }
        return true;
    }


    public boolean move(int column)
    {
        boolean isValidMove = move(current, column);
        if (isValidMove)
        {
            current = -current;
            updateGameStatus(column);
        }

        return isValidMove;
    }


    private boolean move(int player, int column)
    {
        if (status != GameStatus.Gaming)
            return false;
        for (int i = 0; i < HEIGHT; ++i)
        {
            if (grid[column][i] == 0)
            {
                grid[column][i] = player;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (int y = HEIGHT - 1; y >= 0; --y)
        {
            for (int x = 0; x < WIDTH; ++x)
            {
                sb.append(grid[x][y]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean isInside(int x, int y)
    {
        return x >= 0 && x < 7 &&
                y >= 0 && y < 6;
    }
}
