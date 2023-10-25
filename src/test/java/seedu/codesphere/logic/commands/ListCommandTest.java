package seedu.codesphere.logic.commands;

import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codesphere.logic.commands.CommandTestUtil.showCourseAtIndex;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCourseList(), new UserPrefs());
        expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
