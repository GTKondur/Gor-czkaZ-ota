package edu.io.player;

import edu.io.token.*;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();

    public Player() {}

    public Player(PlayerToken token) {
        this.token = token;
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public Gold gold() {
        return gold;
    }

    public void interactWithToken(Token token) {
        switch (token) {

            case GoldToken goldToken -> {
                double amount = goldToken.amount();
                Tool tool = shed.getTool();

                if (tool instanceof PickaxeToken pickaxe) {
                    pickaxe.useWith(goldToken)
                            .ifWorking(() -> gold.gain(amount * pickaxe.gainFactor()))
                            .ifBroken(() -> {
                                gold.gain(amount);
                                shed.dropTool();
                            });
                } else {
                    gold.gain(amount);
                }
            }

            case PickaxeToken pickaxeToken -> {
                shed.add(pickaxeToken);
            }

            case AnvilToken anvilToken -> {
                if (shed.getTool() instanceof Repairable tool) {
                    tool.repair();
                }
            }

            default -> { }
        }
    }
}
