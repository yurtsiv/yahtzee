package main;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DiceGame {
    private Player[] players;
    private int currentTurn = 1, currentPlayerIndex;
    private final int gameTurns = 3;

    public void initialize () {
        int playersAmount;
        String playerName;

        playersAmount = UserInteraction.getInt("Players amount (2-4):", 2, 4);
        players = new Player[playersAmount];
        for (int i = 0; i < playersAmount; i++) {
            playerName = UserInteraction.question("Player " + i + "'s name:");
            players[i] = new Player(playerName);
        }

        currentPlayerIndex = 0;
    }

    public void turn () {
        System.out.println("-----------------------------");
        Player currentPlayer = players[currentPlayerIndex];
        int rollNum = 1;
        Dices dicesToRoll = new Dices(5);
        List<Integer> keptDices = new ArrayList<>(), turnResult = new ArrayList<>();
        boolean shouldContinueTurn = true;

        System.out.println(currentPlayer.getName() + "'s turn #" + currentTurn);

        while (shouldContinueTurn && rollNum <= 3) {
            System.out.println("Roll #" + rollNum);

            if (keptDices.size() != 0) {
                System.out.print("Kept dices: ");
                Dices.printArbitrary(keptDices);
                if (UserInteraction.yesNoQuestion("Do you want to take some kept dices?")) {
                    int[] dicesIndexesToTake = UserInteraction.getIntArray("Select dices you want to take:");
                    for (int indexToTake : dicesIndexesToTake) {
                        keptDices.remove(indexToTake - 1);
                    }

                    dicesToRoll.setAmount(dicesToRoll.getAmount() + dicesIndexesToTake.length);
                }
            }

            dicesToRoll.roll();
            System.out.print("Roll result: ");
            dicesToRoll.print();

            if (rollNum != 3) {
                shouldContinueTurn = UserInteraction.yesNoQuestion("Do you want to continue your turn?");
                if (shouldContinueTurn) {
                    if (UserInteraction.yesNoQuestion("Do you want to keep any dices?")) {
                        int[] dicesIndexesToKeep = UserInteraction.getIntArray("Select dices you want to keep:");
                        keptDices.addAll(ArrayUtils.takeIndexes(dicesIndexesToKeep, dicesToRoll.getState()));
                        dicesToRoll.setAmount(dicesToRoll.getAmount() - dicesIndexesToKeep.length);
                    }
                }
            }

            rollNum++;
        }

        // add remaining results to turn result
        for (int dice : keptDices) {
            turnResult.add(dice);
        }
        for (int dice : dicesToRoll.getState()) {
            turnResult.add(dice);
        }

        System.out.print("Your turn is finished. Result: ");
        for (int dice : turnResult) {
            System.out.print(" | " + dice);
        }
        System.out.print(" |\n");

        currentPlayer.updateScore(turnResult.stream().mapToInt(Integer::intValue).toArray());

        if (players.length - 1 == currentPlayerIndex) {
            currentPlayerIndex = 0;
            currentTurn++;
        } else {
            currentPlayerIndex++;
        }
    }

    public Player[] getPlayers() { return players; }

    public int getTurns () { return gameTurns; }

    public static void main(String[] args) {
	    DiceGame game = new DiceGame();
	    game.initialize();

        for (int i = 0; i < game.getTurns() * game.getPlayers().length; i++) {
            game.turn();
        }

	    System.out.println("---- Game finished ----- \n");
        Player winner = game.getPlayers()[0];
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + " 's results: \n");
            player.getScoreTable().print();
            int totalScore = player.getScoreTable().getTotal();
            System.out.println("\n Total score: " + totalScore);

            System.out.println("------------------------- \n");
            if (totalScore > winner.getScoreTable().getTotal()) {
                winner = player;
            }
        }

        System.out.println(winner.getName() + " wins!");
    }
}
