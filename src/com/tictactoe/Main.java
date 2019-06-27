package com.tictactoe;
import javax.swing.JFrame;
public class Main {

    public static void main(String[] args)
    {
            //run the tic tac toe game
        JFrame tictac = new TicTacToeGame();
        tictac.setTitle("Anwar's Tic Tac Toe Game!");
        tictac.setSize(400, 400);
        tictac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tictac.setLocationRelativeTo(null);
        tictac.setVisible(true);
    }
}
