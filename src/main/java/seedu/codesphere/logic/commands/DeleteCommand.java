package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.person.UniqueStudentList;

/**
 * Deletes a student identified using it's displayed index from the student list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();
        UniqueStudentList lastShownList = course.getStudentList();

        if (targetIndex.getZeroBased() >= course.getCourseSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.getStudent(targetIndex);
        course.removeStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, Messages.format(studentToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
