package main;

import java.util.Random;
import java.util.List;

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
        this.state = new int[newAmount];
        this.amount = newAmount;
    }

    public int getAmount () { return this.amount; }

    public void print () {
        for (int dice : this.state) {
            System.out.print(" | " + dice);
        }

        System.out.print(" | \n");
    }

    public static void printArbitrary (List<Integer> dices) {
        for (int dice : dices) {
            System.out.print(" | " + dice);
        }

        System.out.print(" | \n");
    }
}
