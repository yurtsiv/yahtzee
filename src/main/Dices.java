package main;

import java.util.Random;

public class Dices {
    private int amount, sides;
    private Random random = new Random();

    public Dices (int amount, int sides, int mod) {
        this.amount = amount;
        this.sides = sides;
    }

    public Dices (int amount) {
        this.amount = amount;
        this.sides = 6;
    }

    public int[] roll () {
        int [] result = new int[this.amount];
        for (int i = 0; i < this.amount; i++) {
            result[i] = this.random.nextInt(this.sides + 1);
        }

        return result;
    }

    public void setAmount (int newAmount) {
        this.amount = newAmount;
    }
}
