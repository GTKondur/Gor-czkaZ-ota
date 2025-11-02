package edu.io.token;

public class PickaxeToken extends Token {
    private final double gainFactor;
    private int durability;
    private State state = State.IDLE;

    private enum State {
        WORKING, BROKEN, IDLE
    }

    public PickaxeToken() {
        this(1.5, 3);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Gain factor must be positive");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive");
        }
        this.gainFactor = gainFactor;
        this.durability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    public void use() {
        if (!isBroken()) {
            durability--;
            if (durability == 0) {
                state = State.BROKEN;
            }
        }
    }

    public PickaxeToken useWith(Token token) {
        if (isBroken()) {
            state = State.BROKEN;
            return this;
        }

        if (token instanceof GoldToken) {
            use();
            state = isBroken() ? State.BROKEN : State.WORKING;
        } else {
            state = State.IDLE;
        }

        return this;
    }

    public PickaxeToken ifWorking(Runnable action) {
        if (state == State.WORKING) action.run();
        return this;
    }

    public PickaxeToken ifBroken(Runnable action) {
        if (isBroken()) action.run();
        return this;
    }

    public PickaxeToken ifIdle(Runnable action) {
        if (state == State.IDLE) action.run();
        return this;
    }
}
