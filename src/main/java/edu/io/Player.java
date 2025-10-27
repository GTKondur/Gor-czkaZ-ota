package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {

    private PlayerToken token;
    private double gold;

    public Player() {
        this.gold = 0.0;
    }

    public Player(PlayerToken token) {
        this.token = token;
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double amonut) {
        if (amonut < 0) {
            throw new IllegalArgumentException("Cannot gain negative gold ");
        }
        gold += amonut;
    }
    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot lose negative gold");
        }
        if (amount > gold) {
            throw new IllegalArgumentException("Cannot lose more gold than you have");
        }
        gold -= amount;
    }
    public void interactWithToken(Token token){
        if(token instanceof GoldToken goldToken){
            gainGold(goldToken.amount());
        }

    }

}
