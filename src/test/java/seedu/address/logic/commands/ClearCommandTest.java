package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalCourses.getTypicalCourses;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.TypicalStudents;

public class ClearCommandTest {
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_emptyStudentList_success() {
        Course validCourse = getTypicalCourses().get(1);

        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(validCourse);

        CommandResult commandResult = new ClearCommand().execute(model);
        String expectedMessage = ClearCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_nonEmptyStudentList_success() {
        CourseBuilder courseBuilder = new CourseBuilder().withStudents(TypicalStudents.getTypicalStudentList());
        Course validCourse = courseBuilder.build();
        StageManager.getInstance().setCourseStage(validCourse);

        CommandResult commandResult = new ClearCommand().execute(model);
        String expectedMessage = ClearCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

}
