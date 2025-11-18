package edu.io.player;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;
public class Vitals {

    private int hydration;
    public Runnable onDeathCallback;

    public Vitals() {
        hydration = 100;
        onDeathCallback = () -> {};
    }

    public int hydration() {
        return hydration;
    }

    public void hydrate(int amount) {
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("amount must be in range 0-100");
        }
        this.hydration = Math.min(100, this.hydration + amount);
    }

    public void dehydrate(int amount) {
        if (amount < 0) throw new IllegalArgumentException("amount cannot be negative");
        this.hydration -= amount;
        if (this.hydration <= 0) {
            this.hydration = 0;
            onDeathCallback.run();
        }
    }

    public boolean isAlive() {
        return hydration > 0;
    }

    @NotNull
    public void setOnDeathHandler(@NotNull Runnable callback) {
        this.onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }

}
