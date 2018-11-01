package main;

import java.util.Random;

public class Dices {
    private int amount, sides;
    private Random random = new Random();
    private int[] state;

    public Dices (int amount, int sides, int mod) {
        this.amount = amount;
        this.sides = sides;
    }

    public Dices (int amount) {
        this.amount = amount;
        this.sides = 6;
    }

    public void roll () {
        int[] result = new int[this.amount];
        for (int i = 0; i < this.amount; i++) {
            result[i] = this.random.nextInt(this.sides) + 1;
        }

        this.state = result;
    }

    public int[] getState () {
        return this.state;
    }

    public void setAmount (int newAmount) {
        this.amount = newAmount;
    }

    public void print () {
        for (int dice : this.state) {
            System.out.print(" | " + dice);
        }
        System.out.println();
    }
}
