package com.company;


import sound.soundManger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Input Manager take the user input and convert it to legal input
 *
 * @author Xiangxin Kong
 * @version 1.0
 */
public class InputManger implements MouseListener, KeyListener {
    board gameBoard;
    MainScreen screen;

    /**
     * @param gameBoard the main controll board of the game
     * @param screen    the user interface of the game
     */
    InputManger(board gameBoard, MainScreen screen) {
        this.gameBoard = gameBoard;
        this.screen = screen;
    }

    /**
     * take mouse click input and convert it to legal input.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 30 - 1;
        int y = e.getY() / 30 - 2;
        if (x < 0 || y < 0) return;
        if (MouseEvent.BUTTON1 == e.getButton()) {
            gameBoard.check(x, y);//left click
        } else if (MouseEvent.BUTTON3 == e.getButton()) {
            gameBoard.marked(x, y);//right click
        }
        screen.repaint();//refresh the screen after clicking}
        screen.checkOver();//
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

