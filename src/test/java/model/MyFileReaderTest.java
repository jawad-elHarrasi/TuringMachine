package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFileReaderTest {

    @Test
    void readFileWitchNotExist() {
        MyFileReader fileReader = new MyFileReader();
        assertThrows(Exception.class, () -> fileReader.readFile(19, "/known"));
    }

    @Test
    void readKnownProblem() {
        MyFileReader fileReader = new MyFileReader();
        String fileContent = "5,1,3,534,2,6,14,17";
        assertEquals(fileContent, fileReader.readFile(5,"/known_problems.csv"));
    }

}