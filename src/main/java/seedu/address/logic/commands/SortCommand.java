package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.person.Student;

import java.util.Comparator;

/**
 * Finds and lists all students in the class whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all students";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students with names containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();

        // Custom comparator to sort students by their student ranks
        Comparator<Student> tagComparator = Comparator.comparing(student -> student.getTag().ranking);

        course.updateSortedStudentList(tagComparator);

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
