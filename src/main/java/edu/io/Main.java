package edu.io;

import edu.io.token.Token;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        board.clean();

        Token player = new Token("웃");
        Token gold = new Token("💰︎");

        board.placeToken(2, 1, player);
        board.placeToken(4, 3, gold);

        board.display();
    }
}
