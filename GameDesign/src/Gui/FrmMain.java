package Gui;

import Game.Board;
import Game.GameStatus;
import Game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMain extends JFrame implements ActionListener
{
    JButton[][] btnPiece = new JButton[Board.WIDTH][Board.HEIGHT];
    Board board = new Board();
    JLabel lblGameStatus = new JLabel("", JLabel.CENTER);

    public FrmMain()
    {
        super("Connect 4");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font lblFont = lblGameStatus.getFont();
        Font lblNewFont = new Font(lblFont.getFontName(), Font.BOLD, lblFont.getSize() + 5);
        lblGameStatus.setFont(lblNewFont);

        JPanel pnl = new JPanel();
        pnl.setLayout(new GridBagLayout());

        for (int x = 0; x < 7; ++x)
        {
            for (int y = 0; y < 6; ++y)
            {
                btnPiece[x][y] = new JButton();
                btnPiece[x][y].addActionListener(this);
                btnPiece[x][y].setActionCommand(Integer.toString(x));
                addItemToPanel(pnl, btnPiece[x][y], x, 5 - y, 1, 1, 0, 0, 0, 0);
            }
        }

        addItemToPanel(pnl, lblGameStatus, 0, 6, 7, 1, 0, 0, 0, 0);
        add(pnl);
        flush();
        setVisible(true);
    }

    private void addItemToPanel(JPanel pnl, JComponent c,
                                int x, int y, int horzSpan, int vertSpan,
                                int topInsets, int leftInsets, int bottomInsets, int rightInsets)
    {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = horzSpan;
        gc.gridheight = vertSpan;
        gc.insets = new Insets(topInsets, leftInsets, bottomInsets, rightInsets);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        pnl.add(c, gc);
    }

    private void askForRestartGame()
    {
        if (JOptionPane.showConfirmDialog(this, board.getGameStatus() + ". Do you want to restart the game?",
            "Game Result",  JOptionPane.YES_NO_OPTION) == 0)
        {
            board.restartGame();
            flush();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (board.getGameStatus() != GameStatus.Gaming)
        {
            askForRestartGame();
            return;
        }

        int x = Integer.parseInt(e.getActionCommand());
        if (board.move(x)) // check is valid move
        {
            flush();
            if (board.getGameStatus() != GameStatus.Gaming)
            {
                askForRestartGame();
            }
        }
    }

    /**
     * Refresh buttons background.
     */
    private void flush()
    {
        int[][] boardData = board.getGrid();
        for (int x = 0; x < Board.WIDTH; ++x)
        {
            for (int y = 0; y < Board.HEIGHT; ++y)
            {
                Color c = Color.WHITE; // default colour is white
                switch (boardData[x][y])
                {
                    case -1:
                        c = Color.BLACK;
                        break;
                    case 1:
                        c = Color.RED;
                        break;
                }
                btnPiece[x][y].setBackground(c);
            }
        }

        // To add a blank line, we could add <br />
        // REFER: https://stackoverflow.com/questions/6635730/how-do-i-put-html-in-a-jlabel-in-java
        lblGameStatus.setText("<html><body>" +
                "Current Player: " + Player.toString(board.getCurrentPlayer()) + "<br/>" +
                "Game Status: " + board.getGameStatus() +
                "</body></html>");
    }
}