package main;

import java.util.Scanner;

public class DiceGame {
    private Dices dices = new Dices(5);
    private Player[] players;
    private int currentTurn = 0, maxTurns;

    public void initialize () {
        int playersAmount;
        String playerName;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Amount of players (2-4):");
        playersAmount = userInput.nextInt();
        this.players = new Player[playersAmount];
        for (int i = 0; i < playersAmount; i++) {
            System.out.println("Player " + i + "'s name:");
            playerName = userInput.next();
            this.players[i] = new Player(playerName);
        }

        System.out.println("Turns amount:");
        this.maxTurns = userInput.nextInt();
    }

    public static void main(String[] args) {
	    DiceGame game = new DiceGame();
	    game.initialize();
    }
}
