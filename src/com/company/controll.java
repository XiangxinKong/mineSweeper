package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * get size and mine numbers from user and start the game
 *
 * @author Xiangxin Kong
 * @version 1.0
 */
public class controll extends JPanel implements ActionListener {
    ArrayList<JButton> level;
    String[] buttonLabel;
    JFrame window;

    controll() {
        window = new JFrame();
        window.add(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 100, 400, 500);
        setBackground(new Color(189, 197, 200));
        window.setVisible(true);
        setLayout(null);
        setUpButtons();
    }

    /*
     *place 5 buttons on the panel
     */
    void setUpButtons() {
        level = new ArrayList<>();
        buttonLabel = new String[]{"Beginner", "Easy", "Normal", "Hard", "Customized"};
        for (int i = 0; i < 5; i++) {
            JButton temp = new JButton(buttonLabel[i]);
            temp.setFont(new Font("serif", Font.BOLD, 18));
            temp.addActionListener(this);
            temp.setBounds(100, 80 + 60 * i, 180, 45);
            add(temp);
            level.add(temp);
        }

    }

    /*
     *this method is invoked by clicking button
     *invoke the gameboard with disired difficulties
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int size, mineNum;
        switch (level.indexOf(e.getSource())) {
            case 0:
                size = 10;
                mineNum = 10;
                break;
            case 1:
                size = 10;
                mineNum = 20;
                break;
            case 2:
                size = 15;
                mineNum = 25;
                break;
            case 3:
                size = 20;
                mineNum = 60;
                break;
            default:
                try {
                    size = Integer.parseInt(JOptionPane.showInputDialog("size"));
                    mineNum = Integer.parseInt(JOptionPane.showInputDialog("Number of mines"));
                } catch (Exception e1) {
                    size = 10;
                    mineNum = 10;
                }
        }
        new MainScreen(size, new board(size, mineNum), buttonLabel[level.indexOf(e.getSource())]);
        window.dispose();
    }


}

