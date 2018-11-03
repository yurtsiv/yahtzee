package main;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DiceGame {
    private Dices dices = new Dices(5);
    private Player[] players;
    private int currentTurn = 0, maxTurns, currentPlayerIndex;

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
        this.currentPlayerIndex = 0;
    }

    public void turn () {
        Player currentPlayer = this.players[this.currentPlayerIndex];
        System.out.println(currentPlayer.getName() + "'s turn");

        int rollNum = 1;
        boolean shouldContinueTurn = true;
        List<Integer> turnResult = new ArrayList<>();

        while (shouldContinueTurn && rollNum <= 3) {
            System.out.println(rollNum + " roll:");
            this.dices.roll();
            this.dices.print();

            if (rollNum == 3) {
                // add remaining results to turn result
                for (int dice : this.dices.getState()) {
                    turnResult.add(dice);
                }
            } else {
                shouldContinueTurn = UserInteraction.yesNoQuestion("Do you want to continue your turn?");
                if (shouldContinueTurn) {
                    if (UserInteraction.yesNoQuestion("Do you want to keep any dices?")) {
                        System.out.println("Select dices you want to keep:");
                        int[] dicesToKeep = UserInteraction.getIntArray();
                        for (int i = 0; i < dicesToKeep.length; i++) {
                            turnResult.add(this.dices.getState()[dicesToKeep[i] - 1]);
                        }
                        this.dices.setAmount(this.dices.getAmount() - dicesToKeep.length);
                    }
                } else {
                    // add remaining results to turn result
                    for (int dice : this.dices.getState()) {
                        turnResult.add(dice);
                    }
                }
            }

            rollNum++;
        }

        // player.updateScore(turnResult);
    }

    public static void main(String[] args) {
	    DiceGame game = new DiceGame();
	    game.initialize();
	    game.turn();

	    System.out.println("Game finished");
    }
}
