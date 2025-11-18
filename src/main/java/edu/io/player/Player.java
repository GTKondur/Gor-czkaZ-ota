package edu.io.player;

import edu.io.token.*;
import org.jetbrains.annotations.NotNull;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();
    public final Vitals vitals;

    public Player() {
        this.vitals = new Vitals();
    }

    public Player(@NotNull PlayerToken token) {
        this();
        if (token == null) throw new NullPointerException("token cannot be null");
        this.token = token;
    }

    public void assignToken(@NotNull PlayerToken token) {
        if (token == null) throw new NullPointerException("token cannot be null");
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public Gold gold() {
        return gold;
    }

    public Shed getShed() {
        return shed;
    }

    public void interactWithToken(@NotNull Token token) {
        if (token == null) throw new NullPointerException("token cannot be null");


        if (!vitals.isAlive()) {
            vitals.onDeathCallback.run();
            throw new IllegalStateException("player is dead");
        }

        switch (token) {
            case EmptyToken e -> vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);

            case GoldToken goldToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                var tool = shed.getTool();
                double amount = goldToken.amount();

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

            case PickaxeToken pickaxeToken -> shed.add(pickaxeToken);

            case AnvilToken anvilToken -> {
                var tool = shed.getTool();
                if (tool instanceof Repairable repairable) {
                    repairable.repair();
                    vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                }
            }

            case WaterToken waterToken -> vitals.hydrate(waterToken.amount());

            default -> { }
        }
    }
}
