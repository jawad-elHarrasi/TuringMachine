package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {
    @Test
    void createAInvalidCode() {
        assertThrows(Exception.class, () -> new Code(2222));
    }

    @Test
    void createAInvalidCode2() {
        assertThrows(Exception.class, () -> new Code(879));
    }
    @Test
    void createAInvalidCode3() {
        assertThrows(Exception.class, () -> new Code(139));
    }
    @Test
    void createAInvalidCode4() {
        assertThrows(Exception.class, () -> new Code(912));
    }
    @Test
    void createAInvalidCode5() {
        assertThrows(Exception.class, () -> new Code(000));
    }
}