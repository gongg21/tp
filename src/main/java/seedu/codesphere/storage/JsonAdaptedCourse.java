//@@author gongg21
package seedu.codesphere.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.student.Student;
import seedu.codesphere.model.student.exceptions.DuplicateStudentException;

/**
 * Jackson-friendly version of {@link Course}.
 */
class JsonAdaptedCourse {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Course's %s field is missing!";
    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate students!";

    private final String courseName;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCourse} with the given course details.
     */
    @JsonCreator
    public JsonAdaptedCourse(@JsonProperty("courseName") String courseName,
                             @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.courseName = courseName;
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code Course} into this class for Jackson use.
     */
    public JsonAdaptedCourse(Course source) {
        courseName = source.getCourseName().fullCourseName;
        for (Student student : source.getStudentList()) {
            students.add(new JsonAdaptedStudent(student));
        }
    }

    /**
     * Converts this Jackson-friendly adapted course object into the model's {@code Course} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted course.
     */
    public Course toModelType() throws IllegalValueException {

        if (courseName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CourseName.class.getSimpleName()));
        }
        if (!CourseName.isValidCourseName(courseName)) {
            throw new IllegalValueException(CourseName.MESSAGE_CONSTRAINTS);
        }
        final CourseName modelCourseName = new CourseName(courseName);
        Course course = new Course(modelCourseName);


        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            try {
                course.addStudent(student);
            } catch (DuplicateStudentException e) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
        }

        return course;
    }
}
//@@author gongg21
