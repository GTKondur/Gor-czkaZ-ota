package edu.io;
import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    public static final int size = 8;
    private Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public void placeToken(int x, int y, Token token) {
        grid[y][x] = token;
    }


    public void clean() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                grid[y][x] = new EmptyToken();
            }
        }
    }

    public void display() {

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(grid[y][x].label() + " ");
            }
            System.out.println();
        }
    }

    public Token peekToken(int x, int y){
        return grid[y][x];
    }

    public int size(){
        return size;
    }

    public record Coords(int col, int row){}

    public Coords getAvailableSquare() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] instanceof EmptyToken) {
                    return new Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Board is full!");
    }
}
