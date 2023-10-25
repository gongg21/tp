package seedu.codesphere.model.course;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.person.UniqueStudentList;



/**
 * Represents a Course in the course list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should contain a two- or three-letter prefix, "
                    + "four digits course code and an optional one-letter suffix";
    public static final String VALIDATION_REGEX = "\\w{2,3}\\d{4}\\w?";

    // Course identity field
    public final CourseName courseName;

    // Course data field
    private final UniqueStudentList students;
    private final FilteredList<Student> filteredStudents;

    /**
     * Every field must be present and not null.
     */
    public Course(CourseName courseName) {
        requireNonNull(courseName);
        this.courseName = courseName;
        this.students = new UniqueStudentList();
        this.filteredStudents = new FilteredList<>(this.students.asUnmodifiableObservableList());
    }

    /**
     * Returns the course name.
     */
    public CourseName getCourseName() {
        return courseName;
    }

    /**
     * Returns the list of students in the course.
     */
    public UniqueStudentList getStudentList() {
        return students;
    }

    /**
     * Returns the list of students in the course.
     */
    public int getCourseSize() {
        return this.students.size();
    }

    /**
     * Adds a student to the course being taught by the TA.
     *
     * @param student A student to be added to the course.
     */
    public boolean hasStudent(Student student) {
        return this.students.contains(student);
    }

    /**
     * Adds a student to the course being taught by the TA.
     *
     * @param student A student to be added to the course.
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     * Sets a student to the course being taught by the TA.
     *
     * @param target        The target student to be replaced.
     * @param editedStudent The student to be set.
     */
    public void setStudent(Student target, Student editedStudent) {
        this.students.setStudent(target, editedStudent);
    }

    /**
     * Removes a student from the course being taught by the TA.
     *
     * @param student A student to be removed from the course.
     */
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedStudentList}
     */
    public ObservableList<Student> getFilteredStudentList() {
        return this.filteredStudents;
    }

    /**
     * Returns an unmodifiable view of the filtered list of {@code Student}
     * @param predicate The predicate filter to run on the student list
     */
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns true if both courses are of the same name.
     * This defines a weaker notion of equality between two courses.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseName().equals(getCourseName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("course name", courseName)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return courseName.equals(otherCourse.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName);
    }

}
