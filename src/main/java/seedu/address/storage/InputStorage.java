package seedu.address.storage;

import javafx.util.Pair;

/**
 * Represents a storage for {@link InputStorage}.
 */
public interface InputStorage {

    /**
     * Returns the user input that the current pointer is pointing to as a Pair object.
     */
    Pair<Boolean, String> getInput();

    /**
     * Adds an input into storage.
     */
    void addInput(boolean isValid, String text);

    /**
     * Decrements the pointer to the input.
     */
    void decrementPointer();

    /**
     * Increments the pointer to the input.
     */
    void incrementPointer();
}
