package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Resets the current listed students to the original state.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets the student list to its original state\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Reset student list to original state.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getCurrentCourse();

        course.resetFilteredStudentList();

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ResetCommand;
    }
}
