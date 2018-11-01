package main;

import java.util.Scanner;

public class DiceGame {
    private Dices dices = new Dices(5);
    private Player[] players;
    private int currentTurn = 0, maxTurns;
    private Player currentPlayer;

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
        this.currentPlayer = players[0];
    }

    public void turn () {
        int rollNum = 1;
        boolean isTurnFinished = false;
        int[] turnResult = new int[5];
        Scanner userInput = new Scanner(System.in);

        while (!isTurnFinished) {
            System.out.println(rollNum + " roll:");
            this.dices.roll();
            this.dices.print();
            System.out.println("Select dices you want to keep:");
            int[] dicesToKeep = UserInteraction.getIntArray();

            System.out.println("Do you want to continue your turn? (yes/no):");
            isTurnFinished = userInput.hasNext("yes");
            rollNum++;
        }
    }

    public static void main(String[] args) {
	    DiceGame game = new DiceGame();
	    game.initialize();
	    game.turn();
    }
}
