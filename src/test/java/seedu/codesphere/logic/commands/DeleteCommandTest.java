package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        Course course = model.getFilteredCourseList().get(0);
        Index targetIndex = Index.fromZeroBased(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);
        Student targetStudent = course.getStudentList().getStudent(targetIndex);
        CommandResult commandResult = new DeleteCommand(targetIndex).execute(model);

        assertEquals(String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, Messages.format(targetStudent)),
                commandResult.getFeedbackToUser());
        assertEquals(false, course.hasStudent(targetStudent));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() throws CommandException {
        Course course = model.getFilteredCourseList().get(1);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index outOfBoundIndex = Index.fromOneBased(course.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_STUDENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_STUDENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

}
