package edu.io;
import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    public final int size = 8;
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

    public Token peekToken(int col, int row){
        return grid[row][col];
    }

    public int size(){
        return size;
    }

    public record Coords(int col, int row){}
}
