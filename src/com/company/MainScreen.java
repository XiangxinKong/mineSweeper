package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sound.soundManger;

/**
 * Graphic Interface of the game
 *
 * @author Xiangxin Kong
 * @version 1.0
 */
public class MainScreen extends JPanel implements ActionListener {
    int size, timer = 0, leftBound = 30, upBound = 60;
    board gameBoard;
    JFrame window;
    Timer timerThread;

    /**
     * Initialize the interface with disired size and gameBoard
     */
    public MainScreen(int size, board gameBoard, String title) {
        this.gameBoard = gameBoard;
        this.size = size;
        window = new JFrame();
        window.add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle(title);
        window.setBounds(1000, 500, size * 30 + 90, size * 30 + 150);
        window.setBackground(new Color(155, 155, 155));
        window.setVisible(true);
        addMouseListener(new InputManger(gameBoard, this));
        timerThread = new Timer(1000, this);
        timerThread.start();
    }

    /**
     * paint the grid, block of the interface
     */
    public void paintComponent(Graphics g) {
        g.setFont(new Font("serif", Font.BOLD, 18));
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                g.setColor(getColor(gameBoard.getToplayer(x, y)));
                g.fillRect(x * 30 + leftBound, y * 30 + upBound, 30, 30);
                g.setColor(new Color(255, 255, 255));
                g.drawRect(x * 30 + leftBound, y * 30 + upBound, 30, 30);
                if (gameBoard.getToplayer(x, y) == state.opened || gameBoard.end != 0) paintLabel(g, x, y);
            }
        }
        updateTime(g);
    }

    private void updateTime(Graphics g) {
        g.setColor(new Color(155, 155, 155, 255));
        g.fillRect(0, 0, (size + 2) * 30, 60);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("serif", Font.BOLD, 24));
        g.drawString("Time: " + timer, 33, 40);
        g.drawString("Mines Left: " + (gameBoard.mineNum - gameBoard.markedNum()), (size - 4) * 30, 40);
    }

    /**
     * set the theme color of each block base on state
     */
    private Color getColor(state x) {
        if (x == state.covered) return new Color(78, 78, 78);
        if (x == state.marked) return new Color(242, 92, 100, 255);
        return new Color(155, 155, 155, 255);
    }

    /**
     * paint the text(number of nearby mines) in the block
     */
    private void paintLabel(Graphics g, int x, int y) {
        int mineNum = gameBoard.getMineLayer(x, y);
        if (mineNum == 0) return;
        String label = (mineNum == -1 ? "⊗" : Integer.toString(mineNum));
        g.drawString(label, x * 30 + leftBound + 13, y * 30 + upBound + 25);
    }

    /**
     * if the game is over, show message and exit with info
     */
    public void checkOver() {
        if (gameBoard.end == 0) return;
        timerThread.stop();
        if (gameBoard.end == 1) {
            new soundManger("soundfile\\cheering.wav");
            JOptionPane.showMessageDialog(this, "Congradualation!\n It took you " + timer + " s");
        } else if (gameBoard.end == -1) {
            new soundManger("soundfile\\Explosion.wav");
            JOptionPane.showMessageDialog(this, "You lost");
        }
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer++;
        repaint();
    }
}

