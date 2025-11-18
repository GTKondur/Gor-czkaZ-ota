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

        Token water = new WaterToken();
        board.placeToken(4, 2, water);
    }

    public void start() {
        System.out.println("Game Started");
        board.display();
    }

    public void run() {

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
                System.err.println("You cannot get outside of this board!");
            } catch (IllegalStateException e) {
                System.err.println("You died");
                break;
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
