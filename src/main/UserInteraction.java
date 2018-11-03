package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    public static int[] getIntArray () {
        Scanner userInput = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (userInput.hasNextInt()) {
            list.add(userInput.nextInt());
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public static boolean yesNoQuestion (String question) {
        System.out.println(question + " (yes/no)");
        return new Scanner(System.in).hasNext("yes");
    }
}
