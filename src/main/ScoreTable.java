package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class ScoreTable {
    private HashMap<String, Integer> scoreTable = new HashMap<>();

    public ScoreTable() {
        String[] tableKeys = new String[]{ "ones", "twos", "threes", "fours", "fives", "sixes",
                                            "bonus", "threeOfAKind", "fourOfAKind", "fullHouse",
                                            "smallStraight", "largeStraight", "yahtzee", "chance" };

        for (String key : tableKeys) {
            scoreTable.put(key, null);
        }
    }

    private static int getAmountOf (int diceNumberToFindAmountOf, int[] turnResult) {
        int result = 0;
        for (int dice : turnResult) {
            if (dice == diceNumberToFindAmountOf) {
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
        for (int kind = 1; kind <= 6; kind++) {
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

        return 0;
    }


    private static int getFullHouseResult (int[] turnResult) {
        boolean twoOfSameKind = false, threeOfSameKind = false;

        for (int kind = 1; kind <= 6; kind++) {
            int sameKindCount = 0;

            for (int dice : turnResult) {
                if (dice == kind) {
                    sameKindCount++;
                }
            }

            if (sameKindCount == 2) {
                twoOfSameKind = true;
            }

            if (sameKindCount == 3) {
                threeOfSameKind = true;
            }
        }

        return twoOfSameKind && threeOfSameKind ? 50 : 0;
    }

    private static int getSmallStraightResult (int[] turnResult) {
        // return ArrayUtils.containsUniqElems(4, turnResult) ? 30 : 0;
        return 0;
    }

    private static int getLargeStraightResult (int[] turnResult) {
        // return ArrayUtils.containsUniqElems(5, turnResult) ? 40 : 0;
        return 0;
    }

    private static int getYahtzeeResult (int[] turnResult) {
        return ArrayUtils.areAllElemsEqual(turnResult) ? 50 : 0;
    }

    public int getTotal () {
        int sum = 0;
        for (String key : scoreTable.keySet()) {
            if (scoreTable.get(key) != null) {
                sum += scoreTable.get(key);
            }
        }
        return sum;
    }

    private int getUpperSectionSum () {
        String[] upperSectionKeys = new String[] { "ones", "twos", "threes", "fours", "fives", "sixes" };
        int sum = 0;
        for (String key : upperSectionKeys) {
            if (scoreTable.get(key) != null) {
                sum += scoreTable.get(key);
            }
        }
        return sum;
    }

    private void checkForBonus () {
        if (getUpperSectionSum() >= 63) {
            System.out.println("You get bonus of 63 points");
            scoreTable.put("bonus", 35);
        }
    }

    private void putToTable (String key, int points) {
        System.out.println("You scored " + points + " points");
        scoreTable.put(key, points);
        checkForBonus();
    }

    public void update (String key, int[] turnResult) {
        switch (key) {
            case "ones":
                putToTable("ones", ScoreTable.getSumOf(1, turnResult));
                break;

            case "twos":
                putToTable("twos", ScoreTable.getSumOf(2, turnResult));
                break;

            case "threes":
                putToTable("threes", ScoreTable.getSumOf(3, turnResult));
                break;

            case "fours":
                putToTable("fours", ScoreTable.getSumOf(4, turnResult));
                break;

            case "fives":
                putToTable("fives", ScoreTable.getSumOf(5, turnResult));
                break;

            case "sixes":
                putToTable("sixes", ScoreTable.getSumOf(6, turnResult));
                break;

            case "threeOfAKind":
                putToTable("threeOfAKind", ScoreTable.getSameOfAKindResult(3, turnResult));
                break;

            case "fourOfAKind":
                putToTable("fourOfAKind", ScoreTable.getSameOfAKindResult(4, turnResult));
                break;

            case "fullHouse":
                putToTable("fullHouse", ScoreTable.getFullHouseResult(turnResult));
                break;

            case "smallStraight":
                putToTable("smallStraight", ScoreTable.getSmallStraightResult(turnResult));
                break;

            case "largeStraight":
                putToTable("largeStraight", ScoreTable.getLargeStraightResult(turnResult));
                break;

            case "yahtzee":
                putToTable("yahtzee", ScoreTable.getYahtzeeResult(turnResult));
                break;

            case "chance":
                putToTable("chance", ScoreTable.getSumOf(turnResult));
        }
    }

    public void printAvailableFields () {
        for (String key : scoreTable.keySet()) {
            if (scoreTable.get(key) == null && !key.equals("bonus")) {
                System.out.print("| " + key + " ");
            }
        }

        System.out.print(" |\n");
    }

    public void print () {
        for (String key : scoreTable.keySet()) {
            if (scoreTable.get(key) == null) {
                System.out.println(key + ": 0");
            } else {
                System.out.println(key + ": " + scoreTable.get(key));
            }
        }
    }
}
