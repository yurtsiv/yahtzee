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
        System.out.println();
        for (int i = 0; i < state.length; i++) {
            System.out.println((i + 1) + ". " + (state[i]));
        }
        System.out.println();
    }

    public static void printArbitrary (List<Integer> dices) {
        System.out.println();
        for (int i = 0; i < dices.size(); i++) {
            System.out.println((i + 1) + ". " + (dices.get(i)));
        }
        System.out.println();
    }
}
