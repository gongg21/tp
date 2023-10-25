package seedu.codesphere.testutil;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.course.Course;

/**
 * A utility class to help with building CourseList objects.
 * Example usage: <br>
 *     {@code CourseList cl = new CourseListBuilder().withCourse("John", "Doe").build();}
 */
public class CourseListBuilder {

    private CourseList courseList;

    public CourseListBuilder() {
        courseList = new CourseList();
    }

    public CourseListBuilder(CourseList courseList) {
        this.courseList = courseList;
    }

    /**
     * Adds a new {@code Course} to the {@code CourseList} that we are building.
     */
    public CourseListBuilder withCourse(Course course) {
        courseList.addCourse(course);
        return this;
    }

    public CourseList build() {
        return courseList;
    }
}
