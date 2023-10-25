package seedu.codesphere.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.person.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Course> PREDICATE_SHOW_ALL_COURSES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces course list data with the data in {@code courseList}.
     */
    void setCourseList(ReadOnlyCourseList courseList);

    /** Returns the CourseList */
    ReadOnlyCourseList getCourseList();

    /**
     * Returns true if a course with the same identity as {@code course} exists in the course list.
     */
    boolean hasCourse(Course course);

    /**
     * Deletes the given course.
     * The course must exist in the course list.
     */
    void deleteCourse(Course course);

    /**
     * Adds the given course.
     * {@code course} must not already exist in the course list.
     */
    void addCourse(Course course);

    /**
     * Replaces the given course {@code course} with {@code editedCourse}.
     * {@code course} must exist in the address book.
     * The course name of {@code editedCourse} must not be the same as another existing course in the course list.
     */
    void setCourse(Course target, Course editedCourse);

    /** Returns an unmodifiable view of the filtered course list */
    ObservableList<Course> getFilteredCourseList();

    /**
     * Updates the filter of the filtered course list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCourseList(Predicate<Course> predicate);
}
