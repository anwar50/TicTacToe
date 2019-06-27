package com.tictactoe;
/**
 * Simple tic tac toe game
 * JFrame to hold tic tac toe
 * @Author Anwar Abdi
 */
import javafx.scene.control.Cell;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TicTacToeGame extends JFrame {
    //whos turn it is
    private char whoseTurn = 'X';
    private boolean gameOver = false;
    private final int SIZE = 3;
    //create grid!
    private Cell[][] cell = new Cell[SIZE][SIZE];
    //Create a status label
    JLabel jlabel = new JLabel("Player 1's turn");
        //No argument constructor
    public TicTacToeGame()
    {
        //panel to hold the cells
        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE, 0, 0));
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                panel.add(cell[i][j] = new Cell());
            }
        }
        panel.setBorder(new LineBorder(Color.blue, 1));
        jlabel.setBorder(new LineBorder(Color.white, 1));

        add(panel, BorderLayout.CENTER);
        add(jlabel, BorderLayout.SOUTH);
    }
    //check if the board is full!
    /**
     *
     * @returns True, if the game is full returns false!
     */
    public boolean ifFull()
    {
        for(int i= 0; i < SIZE; i++)
            for(int j=0; j < SIZE; j++)
                if(cell[i][j].getToken() == ' ')
                    return false;
        return true;
    }
    /**
     * checks if the given token has won!
     * @param token
     * @return true if the token has won, otherwise false
     */
    public boolean WonGame(char token)
    {
        //check all rows
        for(int i =0; i < SIZE; i++)
        {
            if((cell[i][0].getToken() == token) && (cell[i][1].getToken() == token) && (cell[i][2].getToken() == token))
            {
                return true;
            }
        }
        //check all columns!
        for(int k =0; k < SIZE; k++)
        {
            if((cell[0][k].getToken() == token) && (cell[1][k].getToken() == token) && (cell[2][k].getToken() == token))
            {
                return true;
            }
        }
        //check for diagonals!

        if((cell[0][0].getToken() == token) && (cell[1][1].getToken() == token) && (cell[2][2].getToken() == token))
        {
            return true;
        }

        if((cell[0][2].getToken() == token) && (cell[1][1].getToken() == token) && (cell[2][0].getToken() == token))
        {
            return true;
        }
        return false;
    }
    public class Cell extends JPanel
    {
        private char token = ' ';
        public Cell()
        {
            setBorder(new LineBorder(Color.BLACK, 2));
            addMouseListener(new MyMouseListener());
        }
        public char getToken()
        {
            return token;
        }
        public void setToken(char t)
        {
            this.token = t;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g)
        {
            Font myfont = new Font("Serif", Font.BOLD, 14);
            super.paintComponent(g);
            if(token == 'X')
            {
                g.drawLine(10, 10, getWidth()-10, getHeight()-10);
                g.drawLine(getWidth()-10, 10, 10, getHeight()-10);
                g.setFont(myfont);
                g.setColor(Color.CYAN);
            }
            else if(token == 'O')
            {
                g.drawOval(10, 10, getWidth()-20, getHeight()-20);
                g.setColor(Color.GREEN);
                g.setFont(myfont);
            }
        }
        private class MyMouseListener extends MouseAdapter
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(gameOver)
                {
                    return;
                }
                //check if cell is empty, then game is not over
                if(token == ' ' && whoseTurn != ' ')
                {
                    setToken(whoseTurn);
                }
                if(WonGame(whoseTurn))
                {
                    jlabel.setText(whoseTurn + " Well done You have won! Game Over!");
                    whoseTurn = ' ';
                    gameOver = true;
                }
                else if (ifFull())
                {
                    jlabel.setText("Tie game! Game Over!");
                    gameOver= true;
                }
                else
                {
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    jlabel.setText(whoseTurn + "'s turn.");
                }
            }
        }
    }
}
