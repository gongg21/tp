package seedu.codesphere.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.person.Student;

/**
 * A utility class to help with building Person objects.
 */
public class CourseBuilder {

    public static final String DEFAULT_COURSE_NAME = "CS2103T";

    private CourseName courseName;
    private List<Student> students;

    /**
     * Creates a {@code CourseBuilder} with the default details.
     */
    public CourseBuilder() {
        courseName = new CourseName(DEFAULT_COURSE_NAME);
        students = new ArrayList<>();
    }

    /**
     * Initializes the CourseBuilder with the data of {@code courseToCopy}.
     */
    public CourseBuilder(Course courseToCopy) {
        courseName = courseToCopy.getCourseName();
        for (Student student : courseToCopy.getStudentList()) {
            students.add(student);
        }
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public CourseBuilder withCourseName(String courseName) {
        this.courseName = new CourseName(courseName);
        return this;
    }

    public Course build() {
        return new Course(courseName);
    }
}
