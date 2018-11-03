package main;

import java.util.Hashtable;
import java.util.Arrays;

public class ScoreTable {
    private Hashtable<String, Integer> upperSection, lowerSection;

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

    private void checkForBonus () {
        int upperSectionSum = 0;
        for (int val : this.upperSection.values()) {
            upperSectionSum += val;
        }

        if (upperSectionSum >= 63) {
            this.upperSection.put("bonus", 35);
        }
    }

    public void put (String key, int[] turnResult) {
        switch (key) {
            case "ones":
                this.upperSection.put("ones", ScoreTable.getSumOf(1, turnResult));
                break;

            case "twos":
                this.upperSection.put("twos", ScoreTable.getSumOf(2, turnResult));
                break;

            case "threes":
                this.upperSection.put("threes", ScoreTable.getSumOf(3, turnResult));
                break;

            case "fours":
                this.upperSection.put("fours", ScoreTable.getSumOf(4, turnResult));
                break;

            case "fives":
                this.upperSection.put("fives", ScoreTable.getSumOf(5, turnResult));
                break;

            case "sixes":
                this.upperSection.put("sixes", ScoreTable.getSumOf(6, turnResult));
                break;

            case "threeOfAKind":
                this.lowerSection.put("threeOfAKind", ScoreTable.getSameOfAKindResult(3, turnResult));
                break;

            case "fourOfAKind":
                this.lowerSection.put("fourOfAKind", ScoreTable.getSameOfAKindResult(4, turnResult));
                break;

            case "fullHouse":
                this.lowerSection.put("fullHouse", ScoreTable.getFullHouseResult(turnResult));
                break;

            case "smallStraight":
                this.lowerSection.put("smallStraight", ScoreTable.getSmallStraightResult(turnResult));
                break;

            case "largeStraight":
                this.lowerSection.put("largeStraight", ScoreTable.getLargeStraightResult(turnResult));
                break;

            case "yahtzee":
                this.lowerSection.put("yahtzee", ScoreTable.getYahtzeeResult(turnResult));
                break;

            case "chance":
                this.lowerSection.put("chance", ScoreTable.getSumOf(turnResult));
        }

        this.checkForBonus();
    }

    public void print () {

    }
}
