package com.company;

import sound.soundManger;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * logical interactions and state variables of the game
 *
 * @author Xiangxin Kong
 * @version 1.0
 */
public class board {

    int size, mineNum;
    boolean initial = false;
    public int end = 0;//0 for tie
    private static int[][] mineLayer;
    private static state[][] topLayer;

    /**
     * Initialize a new Game with size*size blocks and mineNum mines
     */
    board(int size, int mineNum) {
        this.size = size;
        this.mineNum = mineNum;
        mineLayer = new int[size][size];
        topLayer = new state[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(topLayer[i], state.covered);
        }
    }

    /**
     * Randomly fill mineNum mines, but avoid putting mines around (x,y)
     *
     * @param mineNum number of desired mines
     */
    public void fillMine(int mineNum, int initialX, int initialY) {
        int x, y, i = 0;
        while (i < mineNum) {
            x = (int) (Math.random() * size);//[0,size-1]
            y = (int) (Math.random() * size);
            if (mineLayer[x][y] == -1) continue;
            if (Math.abs(x - initialX) <= 1 && Math.abs(y - initialY) <= 1) continue;
            mineLayer[x][y] = -1;
            fillLabel(x, y);
            i++;
        }
    }

    /**
     * label the (x,y) block with number of mines near (x,y)
     */
    private void fillLabel(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int c = y - 1; c <= y + 1; c++) {
                if (i == x && c == y) continue;
                try {
                    if (mineLayer[i][c] == -1) continue;
                    mineLayer[i][c]++;
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    /**
     * check if the game is finished with "win" state, if so, end=-1
     */
    private void over() {
        for (int i = 0; i < size; i++) {
            for (int c = 0; c < size; c++) {
                if (topLayer[i][c] == state.covered) {
                    return;
                }
            }
        }
        end = 1;//game over-> win
    }

    /**
     * user plays mark for position x,y
     */
    public void marked(int x, int y) {
        state temp = topLayer[x][y];
        if (temp == state.opened) {
            return;
        }
        topLayer[x][y] = (temp == state.marked ? state.covered : state.marked);
        new soundManger("soundfile\\click_x.wav");
        over();
    }

    /**
     * user plays check for position x,y
     */
    public void check(int x, int y) {
        if (!initial) {
            fillMine(mineNum, x, y);
            initial = true;
        }

        if (topLayer[x][y] == state.opened) return;
        topLayer[x][y] = state.opened;
        new soundManger("soundfile\\mapcollision.wav");

        if (mineLayer[x][y] == -1) {
            end = -1;//game over -> lost
            return;
        }

        if (mineLayer[x][y] == 0) {//check the nearby block if it is empty
            Set<Integer> s = new HashSet<Integer>();
            progate(x, y, s);
            s.clear();
        }

        over();
    }

    /**
     * expand the empty blocks
     */
    public void progate(int x, int y, Set<Integer> s) {
        if (x <= 0 || y <= 0 || x > size || y >= size) return;
        s.add(x * 100 + y);
        for (int i = max(x - 1, 0); i <= min(x + 1, size - 1); i++) {
            for (int c = max(y - 1, 0); c <= min(y + 1, size - 1); c++) {
                if (mineLayer[i][c] == 0 && !s.contains(i * 100 + c)) {
                    progate(i, c, s);
                }
                topLayer[i][c] = state.opened;
            }
        }
    }

    /**
     * if (x,y) is coverd, opend or marked with red
     *
     * @return top
     */
    public state getToplayer(int x, int y) {
        return topLayer[x][y];
    }

    /**
     * number of mines near (x,y). -1 for having ming on its own
     *
     * @return mineLayer's state at (x,y)
     */
    public int getMineLayer(int x, int y) {
        return mineLayer[x][y];
    }

    /**
     * number of marked grid
     */
    public int markedNum() {
        int markedNum = 0;
        for (int i = 0; i < size; i++) {
            for (int c = 0; c < size; c++) {
                if (topLayer[i][c] == state.marked) {
                    markedNum++;
                }
            }
        }
        return markedNum;
    }
}