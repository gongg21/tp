package seedu.codesphere.testutil;

import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.codesphere.logic.commands.AddCommand;
import seedu.codesphere.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student person) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.ranking.toString() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.ranking).append(" "));
            }
        }
        return sb.toString();
    }
}
