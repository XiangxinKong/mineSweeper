package com.company;

import javax.swing.*;

/**
 * get size and mine numbers from user and start the game
 *
 * @author Xiangxin Kong
 * @version 1.0
 */
public class controll {
    board gameBoard;

    controll() {
        int size, mineNum;

        try {
            size = Integer.parseInt(JOptionPane.showInputDialog("size"));
            mineNum = Integer.parseInt(JOptionPane.showInputDialog("Number of mines"));
        } catch (Exception e) {
            size = 10;
            mineNum = 10;
        }
        gameBoard = new board(size, mineNum);
        new MainScreen(size, gameBoard);

    }
}

