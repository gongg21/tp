package seedu.codesphere.model;

import javafx.collections.ObservableList;
import seedu.codesphere.model.person.Student;

/**
 * Unmodifiable view of a student list.
 */
public interface ReadOnlyStudentList {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
