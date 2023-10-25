package seedu.codesphere.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String MESSAGE_CONSTRAINTS_ENUMS = "Tags names should be GOOD/AVERAGE/POOR";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final StudentRank ranking;

    /**
     * Constructs a {@code Tag}.
     *
     * @param ranking A valid tag name.
     */
    public Tag(StudentRank ranking) {
        requireNonNull(ranking);
        checkArgument(isValidTagName(ranking.toString()), MESSAGE_CONSTRAINTS);
        this.ranking = ranking;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return ranking.toString().equals(otherTag.ranking.toString());
    }

    @Override
    public int hashCode() {
        return ranking.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + ranking.toString() + ']';
    }

}
