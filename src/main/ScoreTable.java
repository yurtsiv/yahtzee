package main;

import java.util.Hashtable;
import java.util.Arrays;

public class ScoreTable {
    private Hashtable<String, Integer> scoreTable;

    private static int getAmountOf (int diceNumberToFindAmounOf, int[] turnResult) {
        int result = 0;
        for (int dice : turnResult) {
            if (dice == diceNumberToFindAmounOf) {
                result ++;
            }
        }
        return result;
    }

    private static int getSumOf (int diceNumber, int[] turnResult) {
        return ScoreTable.getAmountOf(diceNumber, turnResult) * diceNumber;
    }

//    private static int getThreeOfAKindResult (int[] turnResult) {
//    }

    public void put (String key, int[] turnResult) {
        switch (key) {
            case "ones":
                this.scoreTable.put("ones", ScoreTable.getSumOf(1, turnResult));
                break;

            case "twos":
                this.scoreTable.put("twos", ScoreTable.getSumOf(2, turnResult));
                break;

            case "threes":
                this.scoreTable.put("threes", ScoreTable.getSumOf(3, turnResult));
                break;

            case "fours":
                this.scoreTable.put("fours", ScoreTable.getSumOf(4, turnResult));
                break;

            case "fives":
                this.scoreTable.put("fives", ScoreTable.getSumOf(5, turnResult));
                break;

            case "sixes":
                this.scoreTable.put("sixes", ScoreTable.getSumOf(6, turnResult));
                break;

            case "threeOfAKind":
                // sum of all
                this.scoreTable.put("threeOfAKind", 17);
                break;

            case "fourOfAKind":
                // sum of all
                this.scoreTable.put("fourOfAKind", 24);
                break;

            case "fullHouse":
                this.scoreTable.put("fullHouse", 50);
                break;

            case "smallStraight":
                this.scoreTable.put("smallStraight", 30);
                break;

            case "largeStraight":
                this.scoreTable.put("largeStraight", 40);
                break;

            case "yahtzee":
                this.scoreTable.put("yahtzee", 50);
                break;

            case "chance":
                // sum of all
                this.scoreTable.put("chance", 10);
        }
    }

    public void print () {

    }
}
