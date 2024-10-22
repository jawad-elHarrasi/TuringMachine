package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsTest {

    @Test
    void getValidator() {
        Problems problems = new Problems();
        String[] validatorIndexes = new String[4];
        validatorIndexes[0] = "2";
        validatorIndexes[1] = "6";
        validatorIndexes[2] = "14";
        validatorIndexes[3] = "17";
        for (int i = 0; i < 4; i++) {
            assertEquals(validatorIndexes[i], problems.getValidator(5)[i]);
        }
    }

    @Test
    void getSecretCode() throws TuringException {
        Problems problems = new Problems();
        assertEquals(345, problems.getSecretCode(4).getCode());
    }
}