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

    private static int getSumOf (int[] turnResult) {
        return Arrays.stream(turnResult).sum();
    }

    private static int getSumOf (int diceNumber, int[] turnResult) {
        return ScoreTable.getAmountOf(diceNumber, turnResult) * diceNumber;
    }

    private static int getSameOfAKindResult (int amountOfSameKind, int[] turnResult) {
        int result = 0;
        for (int kind = 1; kind <= 5; kind++) {
            int sameKindCount = 0;
            for (int dice : turnResult) {
                if (dice == kind) {
                    sameKindCount++;
                }
                if (sameKindCount == amountOfSameKind) {
                    return ScoreTable.getSumOf(turnResult);
                }
            }
        }
        return result;
    }


    private static int getFullHouseResult (int[] turnResult) {
        boolean twoOfSameKind = false, threeOfSameKind = false;

        for (int kind = 1; kind <= 5; kind++) {
            int sameKindCount = 0;

            for (int dice : turnResult) {
                if (dice == kind) {
                    sameKindCount++;
                }
            }

            twoOfSameKind = sameKindCount == 2;
            threeOfSameKind = sameKindCount == 3;
        }

        return twoOfSameKind && threeOfSameKind ? 50 : 0;
    }

    private static boolean containsUniqElems (int uniqElems, int[] array) {
        return Arrays
                .stream(array)
                .distinct()
                .toArray().length == uniqElems;
    }

    private static int getSmallStraightResult (int[] turnResult) {
        return ScoreTable.containsUniqElems(4, turnResult) ? 30 : 0;
    }

    private static int getLargeStraightResult (int[] turnResult) {
        return ScoreTable.containsUniqElems(5, turnResult) ? 40 : 0;
    }

    private static int getYahtzeeResult (int[] turnResult) {
        return ScoreTable.containsUniqElems(1, turnResult) ? 50 : 0;
    }

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
                this.scoreTable.put("threeOfAKind", ScoreTable.getSameOfAKindResult(3, turnResult));
                break;

            case "fourOfAKind":
                this.scoreTable.put("fourOfAKind", ScoreTable.getSameOfAKindResult(4, turnResult));
                break;

            case "fullHouse":
                this.scoreTable.put("fullHouse", ScoreTable.getFullHouseResult(turnResult));
                break;

            case "smallStraight":
                this.scoreTable.put("smallStraight", ScoreTable.getSmallStraightResult(turnResult));
                break;

            case "largeStraight":
                this.scoreTable.put("largeStraight", ScoreTable.getLargeStraightResult(turnResult));
                break;

            case "yahtzee":
                this.scoreTable.put("yahtzee", ScoreTable.getYahtzeeResult(turnResult));
                break;

            case "chance":
                this.scoreTable.put("chance", ScoreTable.getSumOf(turnResult));
        }
    }

    public void print () {

    }
}
