package seedu.codesphere.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.AppUtil.checkArgument;

/**
 * Represents a Course's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourseName(String)}
 */
public class CourseName {

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should contain a two or three letter prefix, "
                    + "a four digit course code, and an optional one letter suffix";
    public static final String VALIDATION_REGEX = "\\w{2,3}\\d{4}\\w?";

    public final String fullCourseName;

    /**
     * Constructs a {@code CourseName}.
     *
     * @param courseName A valid course name.
     */
    public CourseName(String courseName) {
        requireNonNull(courseName);
        checkArgument(isValidCourseName(courseName), MESSAGE_CONSTRAINTS);
        fullCourseName = courseName.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid course name.
     */
    public static boolean isValidCourseName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullCourseName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseName)) {
            return false;
        }

        CourseName otherCourseName = (CourseName) other;
        return fullCourseName.equals(otherCourseName.fullCourseName);
    }

    @Override
    public int hashCode() {
        return fullCourseName.hashCode();
    }

}
