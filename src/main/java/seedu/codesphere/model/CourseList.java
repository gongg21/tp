package seedu.codesphere.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.UniqueCourseList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class CourseList implements ReadOnlyCourseList {

    private final UniqueCourseList courses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        courses = new UniqueCourseList();
    }

    public CourseList() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public CourseList(ReadOnlyCourseList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Course list with {@code Courses}.
     * {@code Courses} must not contain duplicate persons.
     */
    public void setCourses(List<Course> courses) {
        this.courses.setCourses(courses);
    }

    /**
     * Resets the existing data of this {@code CourseList} with {@code newData}.
     */
    public void resetData(ReadOnlyCourseList newData) {
        requireNonNull(newData);

        setCourses(newData.getCourseList());
    }

    //// Course-level operations

    /**
     * Returns true if a Course with the same identity as {@code Course} exists in the Course list.
     */
    public boolean hasCourse(Course course) {
        requireNonNull(course);
        return courses.contains(course);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addCourse(Course p) {
        courses.add(p);
    }

    /**
     * Replaces the given Course {@code target} in the list with {@code editedCourse}.
     * {@code target} must exist in the Course list.
     * The person identity of {@code editedCourse} must not be the same as
     * another existing Course in the Course list.
     */
    public void setCourse(Course target, Course editedCourse) {
        requireNonNull(editedCourse);

        courses.setCourse(target, editedCourse);
    }

    /**
     * Removes {@code key} from this {@code CourseList}.
     * {@code key} must exist in the Course list.
     */
    public void removeCourse(Course key) {
        courses.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Courses", courses)
                .toString();
    }

    @Override
    public ObservableList<Course> getCourseList() {
        return courses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseList)) {
            return false;
        }

        CourseList otherCourseList = (CourseList) other;
        return courses.equals(otherCourseList.courses);
    }

    @Override
    public int hashCode() {
        return courses.hashCode();
    }
}
