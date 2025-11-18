package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

public class PlayerToken extends Token {
    private final Board board;
    private final Player player;
    private int col;
    private int row;

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        if (player == null) throw new NullPointerException("player cannot be null");
        if (board == null) throw new NullPointerException("board cannot be null");
        this.board = board;
        this.player = player;
        this.col = 0;
        this.row = 0;
        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }

    public enum Move {
        NONE, LEFT, RIGHT, UP, DOWN
    }

    public void move(Move dir) {
        if (dir == null) throw new NullPointerException("Move cannot be null");

        int new_col = col;
        int new_row = row;

        switch (dir) {
            case LEFT -> new_col -= 1;
            case RIGHT -> new_col += 1;
            case UP -> new_row -= 1;
            case DOWN -> new_row += 1;
            case NONE -> { return; }
        }


        if (new_col < 0 || new_row < 0 || new_col >= board.size() || new_row >= board.size()) {
            throw new IllegalArgumentException("Cannot move outside of board!");
        }


        Token tokenAtNewPos = board.peekToken(new_col, new_row);


        player.interactWithToken(tokenAtNewPos);


        if (!(tokenAtNewPos instanceof AnvilToken)) {
            board.placeToken(new_col, new_row, new EmptyToken());
        }


        board.placeToken(col, row, new EmptyToken());
        col = new_col;
        row = new_row;
        board.placeToken(col, row, this);
    }
}
