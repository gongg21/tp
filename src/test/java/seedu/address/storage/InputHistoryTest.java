package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;

public class InputHistoryTest {

    private static final Pair<Boolean, String> EMPTY_PAIR = new Pair<>(true, "");
    private static final String TRUE_STRING = "home";
    private static final String FALSE_STRING = "invalid";

    public void populateHistory(InputHistory inputHistory, int count) {
        for (int i = 1; i <= count; i++) {
            String input = "INPUT_" + count;
            inputHistory.addInput(true, input);
        }
    }

    @Test
    public void emptyHistoryReturnsDefaultPair() {
        InputHistory inputHistory = new InputHistory();
        assertEquals(EMPTY_PAIR, inputHistory.getInput());
    }

    @Test
    public void pointerStartAtMinusOne() {
        InputHistory inputHistory = new InputHistory();
        assertEquals(-1, inputHistory.getInputPointer());
    }

    @Test
    public void pointerNotNegative() {
        InputHistory inputHistory = new InputHistory();
        populateHistory(inputHistory, 10);
        for (int i = 0; i < 20; i++) {
            inputHistory.decrementPointer();
            assertEquals(true, inputHistory.getInputPointer() >= 0);
        }
    }

    @Test
    public void pointerNotExceedSize() {
        InputHistory inputHistory = new InputHistory();
        populateHistory(inputHistory, 10);
        for (int i = 0; i < 10; i++) {
            inputHistory.incrementPointer();
            assertEquals(true, inputHistory.getInputPointer() <= inputHistory.getInputHistorySize());
        }
    }


}
