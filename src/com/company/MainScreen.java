package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;

/**
 * @author Xiangxin Kong
 * @version 1.0
 */

public class MainScreen extends JPanel {

    int size;
    board gameBoard;
    JFrame window;

    public MainScreen(int size, board gameBoard) {
        this.gameBoard = gameBoard;
        window = new JFrame();
        window.add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.size = size;
        window.setBounds(1000, 500, size * 30 + 90, size * 30 + 120);
        window.setBackground(new Color(155, 155, 155));
        window.setVisible(true);
        addMouseListener(new InputManger(gameBoard, this));
    }

    public void paintComponent(Graphics g) {
        g.setFont(new Font("serif", Font.BOLD, 18));
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                g.setColor(getColor(gameBoard.getToplayer(x, y)));
                g.fillRect(x * 30 + 30, y * 30 + 30, 30, 30);
                g.setColor(new Color(255, 255, 255));
                g.drawRect(x * 30 + 30, y * 30 + 30, 30, 30);
                if (gameBoard.getToplayer(x, y) == state.opened ||gameBoard.end!=0) paintLabel(g, x, y);
            }
        }
    }

    private Color getColor(state x) {
        if (x == state.covered) return new Color(78, 78, 78);
        if (x == state.marked) return new Color(242, 92, 100, 255);
        return new Color(155, 155, 155, 255);
    }

    private void paintLabel(Graphics g, int x, int y) {
        int mineNum = gameBoard.getMineLayer(x, y);
        if(mineNum==0)return;
        String label = (mineNum == -1 ? "⊗" : Integer.toString(mineNum));
        g.drawString(label, x * 30 + 43, y * 30 + 55);
    }

    public void checkOver() {
        if (gameBoard.end == 0) return;
        JFrame frame = new JFrame("JOptionPane showMessageDialog example");
        frame.setBounds(100,100,100,100);
        if (gameBoard.end == 1) {
            JOptionPane.showMessageDialog(frame, "Congradualation You Won");
        } else if (gameBoard.end == -1) {
            JOptionPane.showMessageDialog(this, "You lost");
        }
        System.exit(0);
    }

}
