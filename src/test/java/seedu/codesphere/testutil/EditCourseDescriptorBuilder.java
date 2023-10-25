package seedu.codesphere.testutil;

import seedu.codesphere.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;

/**
 * A utility class to help with building EditCourseDescriptor objects.
 */
public class EditCourseDescriptorBuilder {

    private EditCourseDescriptor descriptor;

    public EditCourseDescriptorBuilder() {
        descriptor = new EditCourseDescriptor();
    }

    public EditCourseDescriptorBuilder(EditCourseDescriptor descriptor) {
        this.descriptor = new EditCourseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code student}'s details
     */
    public EditCourseDescriptorBuilder(Course course) {
        descriptor = new EditCourseDescriptor();
        descriptor.setCourseName(course.getCourseName());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCourseDescriptorBuilder withCourseName(String courseName) {
        descriptor.setCourseName(new CourseName(courseName));
        return this;
    }

    public EditCourseDescriptor build() {
        return descriptor;
    }
}
