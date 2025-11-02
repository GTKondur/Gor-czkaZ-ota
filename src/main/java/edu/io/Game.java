package edu.io;

import edu.io.player.Player;
import edu.io.token.*;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player;

    public Game() {
        this.board = new Board();
        this.player = new Player();
    }

    public void start() {
        System.out.println("Game Started");
        board.clean();
        join(player);
        setupBoard();

        board.display();
        System.out.println("Gold amount: " + player.gold());
        game();
    }

    private void setupBoard() {

        board.placeToken(4, 4, new GoldToken());
        board.placeToken(2, 7, new GoldToken());
        board.placeToken(7, 3, new GoldToken());

        board.placeToken(4, 6, new PyriteToken());
    }

    public void join(Player player) {
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }

    private void game() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            String input = scanner.nextLine().trim().toLowerCase();

            try {
                switch (input) {
                    case "w" -> player.token().move(PlayerToken.Move.UP);
                    case "s" -> player.token().move(PlayerToken.Move.DOWN);
                    case "a" -> player.token().move(PlayerToken.Move.LEFT);
                    case "d" -> player.token().move(PlayerToken.Move.RIGHT);

                    default -> System.out.println("Nieznana komenda. Użyj w/s/a/d");
                }

                board.display();
                System.out.println("Gold amount: " + player.gold());

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
