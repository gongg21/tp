package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Deletes a student identified using it's displayed index from the student list.
 */
public class DeleteCourseCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the course identified by the index number used in the displayed course list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_COURSE_SUCCESS = "Deleted course: %1$s";

    private final Index targetIndex;

    public DeleteCourseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Course> lastShownList = model.getFilteredCourseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        }

        Course courseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCourse(courseToDelete);
        model.resetFilteredCourseList();
        return new CommandResult(String.format(MESSAGE_DELETE_COURSE_SUCCESS, Messages.format(courseToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCourseCommand)) {
            return false;
        }

        DeleteCourseCommand otherDeleteCourseCommand = (DeleteCourseCommand) other;
        return targetIndex.equals(otherDeleteCourseCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
