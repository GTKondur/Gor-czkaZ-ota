package edu.io.player;

import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {

    private PlayerToken token;
    public Gold gold = new Gold();
    private PickaxeToken pickaxe;

    public Player() {
        this.gold = new Gold();
    }

    public Player(PlayerToken token) {
        this.token = token;
        this.gold = new Gold();
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }


    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            double amount = goldToken.amount();


            if (pickaxe != null && !pickaxe.isBroken()) {
                amount *= pickaxe.gainFactor();
                pickaxe.use();
            }

            gold.gain(amount);

        } else if (token instanceof PickaxeToken pickaxeToken) {

            this.pickaxe = pickaxeToken;
        }
    }
    public Gold gold() {
        return gold;
    }
}
