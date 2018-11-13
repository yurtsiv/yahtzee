package main;

public class Player {
    private String name;
    private ScoreTable scoreTable = new ScoreTable();

    public Player (String name) {
        this.name = name;
    }

    public String getName () { return name; }

    public ScoreTable getScoreTable () {
        return scoreTable;
    }

    public void updateScore (int[] turnResult) {
        System.out.println("Available categories:");
        scoreTable.printAvailableFields();
        String category = UserInteraction.question("Which category do you choose?");
        scoreTable.update(category, turnResult);
    }
}
