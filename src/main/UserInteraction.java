package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    public static int[] getIntArray (String question) {
        System.out.println(question);
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

    public static int getInt (String question, int lowerBound, int upperBound) {
        int result = -1;
        Scanner scan = new Scanner(System.in);

        while (result < lowerBound || result > upperBound) {
            System.out.println(question);
            try {
                result = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please, enter a number");
                scan.nextLine();
            }
        }

        return result;
    }

    public static String question (String question) {
        System.out.println(question);
        return new Scanner(System.in).next();
    }

    public static void printSeparator () {
        System.out.println("----------------------------------------------------------");
    }
}
