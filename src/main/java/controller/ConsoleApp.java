package controller;

import model.Code;
import model.Game;
import model.TuringException;
import view.consoleInterface.ConsoleView;
import view.consoleInterface.ValidatorConsole;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ConsoleApp is a console-based application for playing a game involving codes and commands.
 * It provides a simple interactive interface for the user to input commands and play the game.
 * <p>
 * The application uses a CommandManager to manage game commands and a Game instance to represent
 * the game state.
 *
 * @author JAWAD EL HARRASI
 * @version 1.0
 */
public class ConsoleApp implements PropertyChangeListener {
    private final static Pattern TEST = Pattern.compile("^\\s*test\\s+\\d\\s*$");
    private final static Pattern INPUT = Pattern.compile("^\\s*input\\s+\\d+\\s*$");
    private final static Pattern NEXT = Pattern.compile("^\\s*next\\s*$");
    private final static Pattern CONFIRM = Pattern.compile("^\\s*confirm\\s*$");
    private final static Pattern UNDO = Pattern.compile("^\\s*undo\\s*$");
    private final static Pattern REDO = Pattern.compile("^\\s*redo\\s*$");
    private final static Pattern QUIT = Pattern.compile("^\\s*quit\\s*$");
    private final static Pattern HELP = Pattern.compile("^\\s*help\\s*$");

    private static final Scanner clavier = new Scanner(System.in);
    private String inputCode = "";
    private String score = "0";
    private String turn = "1";

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used)
     * @throws TuringException If there is an issue with Turing machine operations
     */
    public static void main(String[] args) throws TuringException {
        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.launcher();
    }

    /**
     * Reads a line of input from the console, validating against predefined patterns.
     *
     * @return Validated user input
     */
    private static String readLine() {
        String input = null;
        int cpt = 0;

        do {
            if (input != null && cpt != 0) {
                System.out.println("\033[91m" + "Wrong entry, please retry" + "\033[0m");
            } else {
                System.out.println("\033[94m" + "What do you play now" + "\033[0m");
            }
            cpt++;

            input = clavier.nextLine().toLowerCase();

        } while (!QUIT.matcher(input).matches() && !INPUT.matcher(input).matches() && !NEXT.matcher(input).matches()
                && !CONFIRM.matcher(input).matches() && !UNDO.matcher(input).matches()
                && !REDO.matcher(input).matches() && !TEST.matcher(input).matches() && !HELP.matcher(input).matches());

        return input;
    }



    /**
     * Launches the console application, initializing the game and handling user input.
     *
     * @throws TuringException If there is an issue with Turing machine operations
     */
    private void launcher() throws TuringException {
        Game game = null;
        int level;
        ConsoleView.displayStart();

        boolean back = true;

        while (back) {
            String input = clavier.nextLine();

            if (input.equals("y")) {
                ConsoleView.problemsList();
                level = 1;
                boolean redo = true;
                while (redo) {
                    System.out.println("choose a level between 1 and 16");
                    level = robusterIntEntire();
                    if (level <= 16 && level > 0) {
                        redo = false;
                    }
                }
                game = new Game(level);
                back = false;
            } else if (input.equals("n")) {
                Random random = new Random();
                level = random.nextInt(16) + 1;
                game = new Game(level);
                back = false;
            } else {
                System.out.println("Please type 'y' or 'n'.");
            }

        }
        game.launchGame();
        game.addObserver(this);

        List<String> stringValidatorList = new ArrayList<>();
        List<ValidatorConsole> validatorForConsole = new ArrayList<>();
        for (int i = 0; i < game.getValidatorIndexes().size(); i++) {
            ValidatorConsole validatorConsole = new ValidatorConsole(game.stringOfAValidator(i));
            validatorForConsole.add(validatorConsole);
            game.addObserverForValidator(i, validatorConsole);
            stringValidatorList.add(validatorConsole.toString());
        }

        ConsoleView.displayHelp();

        while (true) {
            try {
                stringValidatorList.clear();
                for (ValidatorConsole validatorConsole : validatorForConsole) {
                    stringValidatorList.add(validatorConsole.toString());
                }

                ConsoleView.validatorList(stringValidatorList);
                ConsoleView.printInput(this.inputCode);
                ConsoleView.printScores(this.score);
                ConsoleView.printTurn(this.turn);


                String input = readLine();
                String[] split = input.split("\\s");
                switch (split[0]) {
                    case "test" -> game.verifyCommand(Integer.parseInt(split[1]) - 1);
                    case "input" -> game.inputCodeCommand(Integer.parseInt(split[1]));
                    case "next" -> game.nextTurnCommand();
                    case "help" -> ConsoleView.displayHelp();
                    case "confirm" -> {
                        if (game.confirmACode()) {
                            System.out.println("\u001B[32m" + "YOU WIN !!!!");
                        } else {
                            System.out.println("\u001B[31m" + "YOU LOSE ");
                        }
                        ConsoleView.displayEnd();
                        game.end();
                    }
                    case "undo" -> game.undo();
                    case "redo" -> game.redo();
                    case "quit" -> {
                        ConsoleView.displayEnd();
                        game.end();
                    }
                }
            } catch (Exception e) {
                ConsoleView.displayError(e.getMessage());
            }

        }
    }

    private int robusterIntEntire() {
        int nb;
        do {
            while (!clavier.hasNextInt()) {
                System.out.println("It's not an integer");
                clavier.next();
            }
            nb = clavier.nextInt();
            clavier.nextLine();
            if (nb < 0) {
                System.out.println("Integer but negative");
            }
        } while (nb < 0);
        return nb;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles property change events, updating relevant properties based on the event's property name.
     * </p>
     *
     * @param evt The property change event.
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "SCORE MODIFICATION" -> this.score = String.valueOf(evt.getNewValue());
            case "INPUT CODE MODIFICATION" -> {

                if (evt.getNewValue() != null) {
                    Code newCode = (Code) evt.getNewValue();
                    this.inputCode = String.valueOf(newCode.getCode());
                } else {
                    this.inputCode = "";
                }
            }
            case "TURN MODIFICATION" -> this.turn = String.valueOf(evt.getNewValue());
        }
    }
}
