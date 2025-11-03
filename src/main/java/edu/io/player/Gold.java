package edu.io.player;

public class Gold {
    private double amount;

    public Gold() {
        this.amount = 0.0;
    }

    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return String.valueOf(amount);
    }

    public void gain(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot gain negative gold");
        }
        amount += value;
    }

    public void lose(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot lose negative gold");
        }
        if (value > amount) {
            throw new IllegalArgumentException("Not enough gold to lose");
        }
        amount -= value;
    }
}
