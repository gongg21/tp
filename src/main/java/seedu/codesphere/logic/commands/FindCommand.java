package seedu.codesphere.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.parser.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.person.NameContainsKeywordsPredicate;
import seedu.codesphere.model.person.UniqueStudentList;

/**
 * Finds and lists all students in the class whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students with names containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();
        UniqueStudentList studentList = course.getStudentList();

        // TO DO UP FIND COMMAND
        course.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, course.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
