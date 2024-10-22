package view.consoleInterface;


import model.Problems;

import java.util.List;

/**
 * ConsoleView provides methods for displaying information and interacting with the user through the console.
 * It includes methods for displaying game-related messages, a list of problems, validator information,
 * scores, turns, and handling errors.
 * <p>
 * ANSI color codes are used for better console formatting.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public class ConsoleView {

    /**
     * ANSI color code for yellow text
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * ANSI color code for resetting text color
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * ANSI color code for green text
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * ANSI color code for purple text
     */
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     * Displays the welcome message and prompts the user to choose a level or continue with a random one.
     */
    public static void displayStart() {
        System.out.println(ANSI_YELLOW + "WELCOME TO THE TURING GAME" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Do you want to choose a level or continue with a random one? " +
                "(Enter 'y' for choosing one, 'n' for a random one)" + ANSI_RESET);
    }

    /**
     * Displays a closing message to thank the user and say goodbye.
     */
    public static void displayEnd() {
        System.out.println(ANSI_PURPLE + "THANK YOU AND GOODBYE" + ANSI_RESET);
    }

    /**
     * Displays the help menu with a list of Turing machine commands and their explanations.
     */
    public static void displayHelp() {
        System.out.println(ANSI_YELLOW + """
                TURING MACHINE COMMANDS:
                input a code: input <code>
                test a validator: test <index of the validator>
                next turn: next
                validate a code: confirm
                undo: undo
                redo: redo
                quit the game: quit
                print this rules: help
                \s""" + ANSI_RESET);
    }

    /**
     * Displays a list of predefined problems for the user to choose from.
     * TODO: Check if this is the correct solution.
     */
    public static void problemsList() {
        Problems problems = new Problems();
        System.out.println(problems);
    }

    /**
     * Displays a list of validators with their corresponding indices.
     *
     * @param validatorList The list of validator strings.
     */
    public static void validatorList(List<String> validatorList) {
        int index = 1;
        for (String validatorString : validatorList) {
            System.out.println(index + " : " + validatorString);
            index++;
        }
    }

    /**
     * Prints the current score.
     *
     * @param score The current score to be displayed.
     */
    public static void printScores(String score) {
        System.out.println("Score: " + score);
    }

    /**
     * Prints the current turn number.
     *
     * @param turn The current turn number to be displayed.
     */
    public static void printTurn(String turn) {
        System.out.println("Turn: " + turn);
    }

    /**
     * Prints the current input code.
     *
     * @param input The current input code to be displayed.
     */
    public static void printInput(String input) {
        System.out.println("Input code: " + input);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static void displayError(String message) {
        System.out.println("\033[91m" + "Warning: " + message + "\033[0m");
        System.out.println("\n");
    }
}
