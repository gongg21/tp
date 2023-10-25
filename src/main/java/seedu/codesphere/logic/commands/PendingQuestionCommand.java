package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDINGQUESTION;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Student;

/**
 * Changes the pending question of an existing person in the address book.
 */
public class PendingQuestionCommand extends Command {

    public static final String COMMAND_WORD = "pq";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the pending question of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing pending question will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PENDINGQUESTION + "[PENDING QUESTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PENDINGQUESTION + "What is the meaning of life?";

    public static final String MESSAGE_ADD_PENDING_QUESTION_SUCCESS = "Added pending question to Person: %1$s";
    public static final String MESSAGE_DELETE_PENDING_QUESTION_SUCCESS = "Removed pending question from Person: %1$s";

    private final Index index;
    private final PendingQuestion pendingQuestion;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param pendingQuestion of the person to be updated to
     */
    public PendingQuestionCommand(Index index, PendingQuestion pendingQuestion) {
        requireAllNonNull(index, pendingQuestion);

        this.index = index;
        this.pendingQuestion = pendingQuestion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager stageManager = StageManager.getCurrent();
        Course course = stageManager.getCurrentCourse();
        List<Student> lastShownList = course.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedPerson = new Student(studentToEdit.getName(), studentToEdit.getEmail(),
                studentToEdit.getRemark(), pendingQuestion, studentToEdit.getTag());

        course.setStudent(studentToEdit, editedPerson);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !pendingQuestion.value.isEmpty()
                ? MESSAGE_ADD_PENDING_QUESTION_SUCCESS : MESSAGE_DELETE_PENDING_QUESTION_SUCCESS;
        return String.format(message, studentToEdit);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PendingQuestionCommand)) {
            return false;
        }

        PendingQuestionCommand e = (PendingQuestionCommand) other;
        return index.equals(e.index)
                && pendingQuestion.equals(e.pendingQuestion);
    }
}
