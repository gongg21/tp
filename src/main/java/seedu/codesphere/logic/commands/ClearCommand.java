package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Course list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCourseList(new CourseList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
