package seedu.codesphere.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codesphere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codesphere.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourses;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.ModelManager;
import seedu.codesphere.model.UserPrefs;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.person.Remark;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = getTypicalCourses().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(REMARK_STUB));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = getTypicalCourses().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(""));
        CommandResult commandResult = remarkCommand.execute(model);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_filteredList_success() {
        Course validCourse = getTypicalCourses().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse);

        showStudentAtIndex(validCourse, INDEX_FIRST_STUDENT);

        Student firstPerson = validCourse.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(validCourse.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_STUDENT, new Remark(REMARK_STUB));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);


        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        expectedModel.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentList().getStudentList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_STUDENT,
                new Remark(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_STUDENT,
                new Remark(VALID_REMARK_BOB))));
    }
}
