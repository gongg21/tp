package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_NAME;

import seedu.address.logic.commands.AddCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.course.Course;


/**
 * A utility class for Course.
 */
public class CourseUtil {

    /**
     * Returns an add command string for adding the {@code course}.
     */
    public static String getAddCourseCommand(Course course) {
        return AddCourseCommand.COMMAND_WORD + " " + getCourseDetails(course);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getCourseDetails(Course course) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COURSE_NAME + course.getCourseName().fullCourseName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCourseDescriptor}'s details.
     */
    public static String getEditCourseDescriptorDetails(EditCourseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCourseName().ifPresent(courseName -> sb.append(PREFIX_COURSE_NAME)
                .append(courseName.fullCourseName).append(" "));
        return sb.toString();
    }
}
