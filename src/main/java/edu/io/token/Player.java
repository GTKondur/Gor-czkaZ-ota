package edu.io.token;

public class Player {
   private PlayerToken token;
   private double gold;

    public Player(PlayerToken token) {
        this.token = token;
    }

    public void assignToken(PlayerToken token){
       this.token=token;
   }
   public PlayerToken token(){
        return token;

   }
   public double gold(){
        return gold;
   }
}
