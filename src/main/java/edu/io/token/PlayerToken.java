package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {
    private Board board;
    private int col;
    private int row;

    public PlayerToken(Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board= board;
        this.col=0;
        this.row=0;
        board.placeToken(col,row,this);
    }
    public Board.Coords pos(){
        return new Board.Coords(col,row);
    }

    public enum Move{
        NONE, LEFT, RIGHT, UP, DOWN
    }

    public void move(Move dir){

        switch (dir){
            case Move.LEFT: col -=1 ; break;
            case Move.RIGHT: col +=1 ; break;
            case Move.UP: row -=1 ; break;
            case Move.DOWN: row +=1 ; break;
        }
        board.placeToken(col, row, this);
    }
}
