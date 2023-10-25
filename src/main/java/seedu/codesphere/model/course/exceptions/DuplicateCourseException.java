package seedu.codesphere.model.course.exceptions;

/**
 * Signals that the operation will result in duplicate Course (Course are considered duplicates if they have the same
 * value).
 */
public class DuplicateCourseException extends RuntimeException {
    public DuplicateCourseException() {
        super("Operation would result in duplicate courses");
    }
}
