package main;

import java.util.List;
import java.util.ArrayList;

public class DiceGame {
    private Player[] players;
    private int currentTurn = 1, currentPlayerIndex, gameRounds;

    public void initialize () {
        int playersNum;
        String playerName;

        playersNum = UserInteraction.getInt("Number of players (2-4):", 2, 4);
        players = new Player[playersNum];
        for (int i = 0; i < playersNum; i++) {
            playerName = UserInteraction.question("Player " + (i + 1) + ":");
            players[i] = new Player(playerName);
        }

        currentPlayerIndex = 0;
        gameRounds = UserInteraction.getInt("Number of rounds (3-13):", 3, 13);
    }

    public void turn () {
        UserInteraction.printSeparator();

        Player currentPlayer = players[currentPlayerIndex];
        int rollNum = 1;
        Dices dicesToRoll = new Dices(5);
        List<Integer> keptDices = new ArrayList<>(), turnResult = new ArrayList<>();
        boolean shouldContinueTurn = true;

        System.out.println(currentPlayer.getName() + "'s turn #" + currentTurn + "\n");

        while (shouldContinueTurn && rollNum <= 3) {
            System.out.println("\nRoll #" + rollNum + "\n");

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
        Dices.printArbitrary(turnResult);

        currentPlayer.updateScore(turnResult.stream().mapToInt(Integer::intValue).toArray());

        if (players.length - 1 == currentPlayerIndex) {
            currentPlayerIndex = 0;
            currentTurn++;
        } else {
            currentPlayerIndex++;
        }
    }

    public Player[] getPlayers() { return players; }

    public int getRounds() { return gameRounds; }

    public static void main(String[] args) {
	    DiceGame game = new DiceGame();
	    game.initialize();

        for (int i = 0; i < game.getRounds() * game.getPlayers().length; i++) {
            game.turn();
        }

	    System.out.println("\n---- Game finished -----\n");
        Player winner = game.getPlayers()[0];
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + " 's results:\n");
            player.getScoreTable().print();
            int totalScore = player.getScoreTable().getTotal();
            System.out.println("\nTotal score: " + totalScore);
            UserInteraction.printSeparator();
            if (totalScore > winner.getScoreTable().getTotal()) {
                winner = player;
            }
        }

        System.out.println(winner.getName() + " wins!");
    }
}
