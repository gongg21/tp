package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Lists all students in the student list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();

        // Resets the student list to its origin state
        course.resetFilteredStudentList();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
