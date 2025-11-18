package edu.io;

import edu.io.player.Player;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();

        player.vitals.setOnDeathHandler(() -> {
            System.out.println("To koniec: pełne odwodnienie");
        });

        game.setupBoard();
        game.join(player);
        game.start();

        game.run();
    }
}
