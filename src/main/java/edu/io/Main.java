package edu.io;
import edu.io.token.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();

        Token pyrite = new PyriteToken();

        Board board = game.getBoard();
        board.clean();

        Token gold = new GoldToken();
        board.placeToken(4,4,gold);
        board.placeToken(2,7,gold);
        board.placeToken(7,3,gold);
        board.placeToken(4,6,pyrite);

        game.join(player);
        game.start();
        Scanner scanner = new Scanner(System.in);

    while (true) {
        try {
            String komenda = scanner.nextLine();
            switch (komenda) {
                case "w":
                    player.token().move(PlayerToken.Move.UP);
                    break;
                case "s":
                    player.token().move(PlayerToken.Move.DOWN);
                    break;
                case "a":
                    player.token().move(PlayerToken.Move.LEFT);
                    break;
                case "d":
                    player.token().move(PlayerToken.Move.RIGHT);
                    break;
            }
            board.display();
            System.out.println("Gold amount: " + player.gold() );
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }



    }
}
