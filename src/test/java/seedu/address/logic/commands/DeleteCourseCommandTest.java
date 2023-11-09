package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCourseAtIndex;
import static seedu.address.testutil.TypicalCourses.CS1101S;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;


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

        assertCommandFailure(deleteCourseCommand, model, Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setHomeStage();

        model.updateFilteredCourseList(new CourseNameContainsKeywordsPredicate(Collections.singletonList(CS1101S
                .getCourseName().fullCourseName)));
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);

        Course courseToDelete = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());
        DeleteCourseCommand deleteCourseCommand = new DeleteCourseCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(DeleteCourseCommand.MESSAGE_DELETE_COURSE_SUCCESS,
                Messages.format(courseToDelete));

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.deleteCourse(courseToDelete);
        expectedModel.resetFilteredCourseList();

        assertCommandSuccess(deleteCourseCommand, model, expectedMessage, expectedModel);
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
