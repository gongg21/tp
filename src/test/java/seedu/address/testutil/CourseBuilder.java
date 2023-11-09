package seedu.address.testutil;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.StudentList;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

/**
 * A utility class to help with building Person objects.
 */
public class CourseBuilder {

    public static final String DEFAULT_COURSE_NAME = "CS2103T";

    private CourseName courseName;
    private UniqueStudentList students;
    private FilteredList<Student> filteredStudents;
    private SortedList<Student> sortedStudents;



    /**
     * Creates a {@code CourseBuilder} with the default details.
     */
    public CourseBuilder() {
        courseName = new CourseName(DEFAULT_COURSE_NAME);
        students = new UniqueStudentList();
        filteredStudents = new FilteredList<>(students.asUnmodifiableObservableList());
        sortedStudents = new SortedList<>(filteredStudents);

    }

    /**
     * Initializes the CourseBuilder with the data of {@code courseToCopy}.
     */
    public CourseBuilder(Course courseToCopy) {
        courseName = courseToCopy.getCourseName();
        students = courseToCopy.getStudentList();
    }

    /**
     * Sets the {@code CourseName} of the {@code Course} that we are building.
     */
    public CourseBuilder withCourseName(String courseName) {
        this.courseName = new CourseName(courseName);
        return this;
    }

    /**
     * Sets the {@code UniqueStudentList} of the {@code Course} that we are building.
     */
    public CourseBuilder withStudents(StudentList studentList) {
        for (Student student : studentList.getStudentList()) {
            this.students.add(student);
        }
        return this;
    }

    /**
     * Builds a {@code Course} with the given courseName and students (if present).
     */
    public Course build() {
        Course course = new Course(courseName);
        if (students != null) {
            for (Student student : students) {
                course.addStudent(student);
            }
        }
        return course;
    }
}
