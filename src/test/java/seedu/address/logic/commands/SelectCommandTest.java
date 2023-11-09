package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;

public class SelectCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        Course selectedCourse = model.getCourseList().getCourseList().get(1);
        Index targetIndex = Index.fromZeroBased(1);
        CommandResult commandResult = new SelectCommand(targetIndex).execute(model);

        assertEquals(String.format(SelectCommand.MESSAGE_SELECT_SUCCESS, Messages.format(selectedCourse)),
                commandResult.getFeedbackToUser());

    }

    @Test
    public void equals() {
        SelectCommand selectFirstCommand = new SelectCommand(INDEX_FIRST_STUDENT);
        SelectCommand selectSecondCommand = new SelectCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectCommand selectFirstCommandCopy = new SelectCommand(INDEX_FIRST_STUDENT);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different Student -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }
}
