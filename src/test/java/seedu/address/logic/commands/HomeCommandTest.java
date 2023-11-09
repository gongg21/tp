package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;

public class HomeCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_stageChangedToHome_success() throws CommandException {
        HomeCommand homeCommand = new HomeCommand();
        String expectedMessage = HomeCommand.MESSAGE_SUCCESS;
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);

        CommandResult commandResult = homeCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_stageAlreadyAtHome_success() throws CommandException {
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        HomeCommand homeCommand = new HomeCommand();
        String expectedMessage = HomeCommand.MESSAGE_HOME_ALREADY;

        CommandResult commandResult = homeCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        HomeCommand homeCommand = new HomeCommand();
        HomeCommand homeCommandOther = new HomeCommand();

        // same object -> returns true
        assertTrue(homeCommand.equals(homeCommand));

        // another home command -> returns true
        assertTrue(homeCommand.equals(homeCommandOther));

        // different types -> returns false
        assertFalse(homeCommand.equals(1));

        // null -> returns false
        assertFalse(homeCommand.equals(null));

    }
}
