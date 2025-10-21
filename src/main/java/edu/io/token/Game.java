package edu.io.token;

import edu.io.Board;

public class Game {
    private Board board;
    private Player player;

    public  Game() {
        this.board =  new Board();

    }
    public void join(Player player){
        this.player = player;
    }
    public void start(){
        System.out.println("Game Started");
        board.display();
    }
}
