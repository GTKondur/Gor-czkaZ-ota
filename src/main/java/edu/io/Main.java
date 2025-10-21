package edu.io;
import edu.io.token.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        game.join(player);
        game.start();
        Board board = new Board();

        board.clean();

        Token gold = new GoldToken();

        board.placeToken(4, 3, gold);
        board.display();
        Scanner scanner = new Scanner(System.in);
    while (true) {
        try {
            String komenda = scanner.nextLine();
            switch (komenda) {
                case "w":
                    player.move(PlayerToken.Move.UP);
                    break;
                case "s":
                    player.move(PlayerToken.Move.DOWN);
                    break;
                case "a":
                    player.move(PlayerToken.Move.LEFT);
                    break;
                case "d":
                    player.move(PlayerToken.Move.RIGHT);
                    break;
            }
            board.display();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }



    }
}
