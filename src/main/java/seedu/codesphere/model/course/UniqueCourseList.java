package seedu.codesphere.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.codesphere.model.course.exceptions.CourseNotFoundException;
import seedu.codesphere.model.course.exceptions.DuplicateCourseException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object)
 * to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Course#isSameCourse(Course)
 */
public class UniqueCourseList implements Iterable<Course> {

    private final ObservableList<Course> internalList = FXCollections.observableArrayList();
    private final ObservableList<Course> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Course toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCourse);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Course toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCourseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setCourse(Course target, Course editedCourse) {
        requireAllNonNull(target, editedCourse);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CourseNotFoundException();
        }

        if (!target.isSameCourse(editedCourse) && contains(editedCourse)) {
            throw new DuplicateCourseException();
        }

        internalList.set(index, editedCourse);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Course toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CourseNotFoundException();
        }
    }

    public void setCourses(UniqueCourseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCourses(List<Course> courses) {
        requireAllNonNull(courses);
        if (!personsAreUnique(courses)) {
            throw new DuplicateCourseException();
        }

        internalList.setAll(courses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Course> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Course> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueCourseList)) {
            return false;
        }

        UniqueCourseList otherUniqueCourseList = (UniqueCourseList) other;
        return internalList.equals(otherUniqueCourseList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(i).isSameCourse(courses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
