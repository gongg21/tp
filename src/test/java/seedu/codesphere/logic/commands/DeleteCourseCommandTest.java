package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.*;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCourseCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Course courseToDelete = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteCourseCommand.MESSAGE_DELETE_COURSE_SUCCESS,
                Messages.format(courseToDelete));

        ModelManager expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.deleteCourse(courseToDelete);

        assertCommandSuccess(deleteCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseList().size() + 1);
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCourseCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);

        Course courseToDelete = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteCourseCommand.MESSAGE_DELETE_COURSE_SUCCESS,
                Messages.format(courseToDelete));

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.deleteCourse(courseToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCourseList().getCourseList().size());

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

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredCourseList(p -> false);

        assertTrue(model.getFilteredCourseList().isEmpty());
    }
}
