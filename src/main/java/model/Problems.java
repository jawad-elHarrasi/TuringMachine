package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Problems} class represents a collection of problems for the game.
 *
 * <p>It reads known problems from a file and provides methods to retrieve
 * information about the problems based on the game level.</p>
 *
 * <p>Instances of this class are created to manage problems for different game levels.</p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Problems {
    private final List<String> problems;

    /**
     * Constructs a new {@code Problems} instance by reading known problems from a file.
     *
     * <p>It initializes the list of problems by reading known problems from the specified file.</p>
     */
    public Problems() {
        this.problems = new ArrayList<>();
        MyFileReader myFileReader = new MyFileReader();
        for (int i = 1; i < 17; i++) {
            problems.add(myFileReader.readFile(i, "/known_problems.csv"));
        }
    }

    /**
     * Gets the validator numbers for a specific level.
     *
     * @param level The game level for which to retrieve validator numbers.
     * @return An array of validator numbers for the specified level.
     */
    public String[] getValidator(int level) {
        int indexBeginValidatorInFile = 4;
        String[] splitProblems = this.problems.get(level - 1).split(",");
        String[] validatorNb = new String[splitProblems.length - 3];
        for (int i = 0; i < 6 && indexBeginValidatorInFile < splitProblems.length; i++) {
            validatorNb[i] = splitProblems[indexBeginValidatorInFile];
            indexBeginValidatorInFile++;
        }
        return validatorNb;
    }

    /**
     * Gets the secret code for a specific level.
     *
     * @param level The game level for which to retrieve the secret code.
     * @return The secret code for the specified level.
     * @throws TuringException if an error occurs while creating the secret code.
     */
    public Code getSecretCode(int level) throws TuringException {
        return new Code(Integer.parseInt(this.problems.get(level - 1).split(",")[3]));
    }

    /**
     * Returns a string representation of all the problems.
     *
     * @return A string containing information about each problem.
     */
    @Override
    public String toString() {
        StringBuilder problems = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int toPrint = i + 1;
            problems.append("problem number ").append(toPrint).append(" difficulty ")
                    .append(this.problems.get(i).split(",")[1]).append("/2  luck ")
                    .append(this.problems.get(i).split(",")[2]).append("/3 \n");
        }
        return problems.toString();
    }
}
