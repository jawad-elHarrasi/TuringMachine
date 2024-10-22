package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * The {@code MyFileReader} class is responsible for reading a specific line from a file
 * based on the provided level and file path.
 *
 * <p>This class uses a BufferedReader to read lines from the specified file until the
 * required level is reached. The content of the desired line is then returned as a string.</p>
 *
 * <p>Instances of this class should be created for reading lines from different files or levels.</p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class MyFileReader {

    /**
     * Constructs a new {@code MyFileReader}.
     */
    public MyFileReader() {

    }

    /**
     * Reads a specific line from a file based on the provided level.
     *
     * @param level The level for which to retrieve the line.
     * @param path  The path to the file.
     * @return The content of the specified line.
     * @throws RuntimeException if the specified line is not found.
     */
    public String readFile(int level, String path) {
        String lineContent = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(MyFileReader.class.getResourceAsStream(path))))) {
            String line;
            int cptLevel = 1;

            while ((line = reader.readLine()) != null && cptLevel <= level + 1) {
                if (cptLevel == level + 1) {
                    lineContent = line;
                }
                cptLevel++;
            }

        } catch (IOException e) {
            throw new RuntimeException("Line not found in the specified file (class MyFileReader)", e);
        }
        return lineContent;
    }
}
