package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private final int maxDurability;
    private int durability;
    private Token withToken = new EmptyToken();

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) throw new IllegalArgumentException("gainFactor must be > 0");
        if (durability <= 0) throw new IllegalArgumentException("durability must be > 0");
        this.gainFactor = gainFactor;
        this.durability = durability;
        this.maxDurability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public void use() {
        if (durability > 0) durability--;
    }

    @Override
    public void repair() {
        this.durability = maxDurability;
    }

    @Override
    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public Tool useWith(Token token) {
        this.withToken = token;
        if (token instanceof GoldToken) {
            if (!isBroken()) {
                use();
            }
        }
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (withToken instanceof GoldToken && !isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!(withToken instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}
