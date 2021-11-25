package main;

import calculations.Rational;
import game.Game;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
    private static int getUserNumerator() {
        System.out.println("Enter your number's numerator: ");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    private static int getUserDenominator() {
        System.out.println("Enter your number's denominator: ");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    private static String getUserName() {
        System.out.println("A new game is starting...");
        System.out.println("Enter your name:");
        return new Scanner(System.in).nextLine();
    }

    private static int getRange() {
        System.out.println("Choose a range: ");
        int range = Integer.parseInt(new Scanner(System.in).nextLine());
        while (range < 4) {
            System.out.println("Range should be at least 4. Enter a new one");
            range = Integer.parseInt(new Scanner(System.in).nextLine());
        }
        return range;
    }

    private static void validateUserNumber(int numerator, int denominator, int range) {
        while (denominator > range || numerator > range) {
            System.out.println("Numbers are out of range. Try again");
            System.out.println(range);
            numerator = getUserNumerator();
            denominator = getUserDenominator();
        }
    }

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.addHandler(new FileHandler("game.log", true));
        logger.setUseParentHandlers(false);

        Game game = new Game();
        String userName = getUserName();
        logger.info("User name: " + userName);
        int range = getRange();
        logger.info("Range: " + range);
        game.start(range);

        System.out.println(game.getNumber());

        for (int i = 1; i <= game.getMaxAttempts(); i++) {
            System.out.printf("Guess #%d%n", i);

            int userNumerator = getUserNumerator();
            int userDenominator = getUserDenominator();

            validateUserNumber(userNumerator, userDenominator, range);

            logger.info("Attempt #" + i + ":" + userNumerator + "/" + userDenominator);

            Rational userNumber = new Rational(userNumerator, userDenominator);
            Rational error = Rational.subtract(userNumber, game.getNumber());

            switch (Integer.signum(error.getNumerator())) {
                case 0 -> {
                    System.out.println("You won!");
                    game.setGuessed(true);
                    logger.info("User won");
                }
                case 1 -> System.out.println("Too big!");
                case -1 -> System.out.println("Too small!");
            }

            if (game.getGuessed()) {
                break;
            }
            game.incrementAttemptsCounter();
            int attemptsLeft = game.getMaxAttempts() - game.getAttemptsCounter();
            if (attemptsLeft > 0) {
                System.out.printf("You have %d more attempts%n", attemptsLeft);
            } else {
                System.out.println("No more attempts. You lost!");
                logger.info("User lost");
            }
        }





    }

}
