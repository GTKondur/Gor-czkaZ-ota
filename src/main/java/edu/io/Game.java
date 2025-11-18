package edu.io;

import edu.io.player.Player;
import edu.io.token.*;
import java.util.Scanner;

public class Game {
    private final Board board;
    private Player player;

    public Game() {
        this.board = new Board();
    }

    public void join(Player player) {
        this.player = player;
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }

    public void setupBoard() {
        board.clean();

        // Gold tokens
        Token gold = new GoldToken();
        board.placeToken(4, 4, gold);
        board.placeToken(2, 7, gold);
        board.placeToken(7, 3, gold);


        Token pyrite = new PyriteToken();
        board.placeToken(4, 6, pyrite);


        Token pickaxe = new PickaxeToken();
        board.placeToken(2, 2, pickaxe);


        Token anvil = new AnvilToken();
        board.placeToken(4, 5, anvil);
    }

    public void start() {
        System.out.println("Game Started");
        board.display();
    }

    public void run() {
        if (player == null) {
            System.err.println("Brak gracza! Najpierw wywołaj join(player).");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String cmd = scanner.nextLine();

            try {
                switch (cmd) {
                    case "w" -> player.token().move(PlayerToken.Move.UP);
                    case "s" -> player.token().move(PlayerToken.Move.DOWN);
                    case "a" -> player.token().move(PlayerToken.Move.LEFT);
                    case "d" -> player.token().move(PlayerToken.Move.RIGHT);
                    default -> System.out.println("Unknown command!");
                }

                board.display();
                System.out.println("Gold amount: " + player.gold().amount());
                System.out.println("Hydration: " + player.vitals.hydration() + "/100");

            } catch (IllegalArgumentException e) {
                System.err.println("Nie możesz wyjść poza planszę!");
            } catch (IllegalStateException e) {
                System.err.println("Umierasz! Brak wody.");
                break;
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
