package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class PendingQuestion {

    public final String value;

    /**
     * Constructs a {@code Pending Question}.
     *
     * @param pendingQuestion A valid pending question.
     */
    public PendingQuestion(String pendingQuestion) {
        requireNonNull(pendingQuestion);
        value = pendingQuestion;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PendingQuestion // instanceof handles nulls
                && value.equals(((PendingQuestion) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
