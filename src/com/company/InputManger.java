package com.company;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Xiangxin Kong
 * @version 1.0
 */
public class InputManger implements MouseListener {
    board gameBoard;
    MainScreen screen;
    InputManger(board gameBoard, MainScreen screen) {
        this.gameBoard = gameBoard;
        this.screen=screen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 30 - 1;
        int y = e.getY() / 30 - 1;
        if(x<0 ||y<0)return;
        if (MouseEvent.BUTTON1 == e.getButton()) {
            gameBoard.check(x, y);
        } else if (MouseEvent.BUTTON3 == e.getButton()) {
            gameBoard.marked(x, y);
        }
        screen.repaint();
        screen.checkOver();
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
}

