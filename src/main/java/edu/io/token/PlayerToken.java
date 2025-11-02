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
        this.board= board;
        this.player = player;
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
        int new_col=col;
        int new_row=row;

        board.placeToken(col,row, new EmptyToken());

        switch (dir){
            case LEFT: new_col -=1 ; break;
            case RIGHT: new_col +=1 ; break;
            case UP: new_row -=1 ; break;
            case DOWN: new_row +=1 ; break;
            case NONE: return;
        }
        if(new_col<0 || new_row<0 || new_col>=board.size() || new_row>=board.size()){
            throw new IllegalArgumentException("Nie możesz wyjść poza plansze!");

        }

        var token = board.peekToken(new_col, new_row);

        if(token instanceof GoldToken gold){
            player.interactWithToken(token);
        }
        board.placeToken(col,row, new EmptyToken());

        col = new_col;
        row = new_row;

        board.placeToken(col, row, this);
    }
}
