package edu.io;

import edu.io.token.PlayerToken;

public class Game {
    private Board board;
    private Player player;

    public  Game() {
        this.board =  new Board();

    }
    public void join(Player player){

        this.player = player;
        PlayerToken token = new PlayerToken(player,board);
        player.assignToken(token);
    }

    public void start(){
        System.out.println("Game Started");
        board.display();
    }

    public Board getBoard(){
        return board;
    }
}
