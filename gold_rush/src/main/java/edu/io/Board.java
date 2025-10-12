package edu.io;

public class Board {
    public final int size = 5;
    private Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public Token square(int x, int y) {
        return grid[y][x];
    }

    public void placeToken(int x, int y, Token token) {
        grid[y][x] = token;
    }


    public void clean() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Token("・");
            }
        }
    }

    public void display() {

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(grid[y][x].label + " ");
            }
            System.out.println();
        }
    }
}
