package com.company;

import java.util.Arrays;

public class board {

    int size, mineNum;
    boolean initial = false;
    public int end = 0;
    private static int mineLayer[][];
    private static state topLayer[][];

    board(int size, int mineNum) {
        this.size = size;
        this.mineNum = mineNum;
        mineLayer = new int[size][size];
        topLayer = new state[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(topLayer[i], state.covered);
        }
    }

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

    private void fillLabel(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int c = y - 1; c <= y + 1; c++) {
                if (i == x && c == y) continue;
                try {
                    if (mineLayer[i][c] == -1) continue;
                    mineLayer[i][c]++;
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

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

    public void marked(int x, int y) {
        state temp = topLayer[x][y];
        if (temp == state.opened) {
            return;
        }
        topLayer[x][y] = (temp == state.marked ? state.covered : state.marked);
        over();
    }

    public void check(int x, int y) {
        if (!initial) {
            fillMine(mineNum, x, y);
            initial = true;
        }
        topLayer[x][y] = state.opened;
        if (mineLayer[x][y] == -1) {
            end = -1;//game over -> lost
            return;
        }

        if (mineLayer[x][y] == 0) {//expand
            for (int i = x - 1; i <= x + 1; i++) {
                for (int c = y - 1; c <= y + 1; c++) {
                    try {
                        if (mineLayer[i][c] == 0) {
                            topLayer[i][c] = state.opened;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        over();
    }

    public state getToplayer(int x, int y) {
        return topLayer[x][y];
    }

    public int getMineLayer(int x, int y) {
        return mineLayer[x][y];
    }

    ;
}
