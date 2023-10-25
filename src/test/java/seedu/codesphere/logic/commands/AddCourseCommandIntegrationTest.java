package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCourseCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCourseList(), new UserPrefs());
    }

    @Test
    public void execute_newCourse_success() {
        Course validCourse = new CourseBuilder().build();

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.addCourse(validCourse);

        assertCommandSuccess(new AddCourseCommand(validCourse), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validCourse)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course courseInList = model.getCourseList().getCourseList().get(0);
        assertCommandFailure(new AddCourseCommand(courseInList), model,
                AddCourseCommand.MESSAGE_DUPLICATE_COURSE);
    }

}
