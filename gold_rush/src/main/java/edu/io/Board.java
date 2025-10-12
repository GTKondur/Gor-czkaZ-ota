package edu.io;

public class Board {
    public final int size = 5;
    private Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }



}
