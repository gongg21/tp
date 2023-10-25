package seedu.codesphere.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.course.Course;

/**
 * An Immutable CourseList that is serializable to JSON format.
 */
@JsonRootName(value = "CourseList")
class JsonSerializableCourseList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Course list contains duplicate course(s).";

    private final List<JsonAdaptedCourse> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableCourseList(@JsonProperty("courses") List<JsonAdaptedCourse> courses) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyCourseList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCourseList}.
     */
    public JsonSerializableCourseList(ReadOnlyCourseList source) {
        persons.addAll(source.getCourseList().stream().map(JsonAdaptedCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CourseList toModelType() throws IllegalValueException {
        CourseList courseList = new CourseList();
        for (JsonAdaptedCourse jsonAdaptedCourse : persons) {
            Course course = jsonAdaptedCourse.toModelType();
            if (courseList.hasCourse(course)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            courseList.addCourse(course);
        }
        return courseList;
    }

}
