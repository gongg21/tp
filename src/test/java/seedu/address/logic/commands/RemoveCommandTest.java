package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RemoveCommand.MESSAGE_EDIT_STUDENT_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Student;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalIndexes;

public class RemoveCommandTest {
    private static final String REMARK_STUB = "";
    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null,
                new RemoveCommand.EditStudentDescriptor()));
    }

    @Test
    public void constructor_nullStudentDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_removeRemarkUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withRemark("").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_removePendingQuestionUnfilteredList_success() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void execute_removePendingQuestionUnfilteredList_failure() throws CommandException {
        Course validCourse = new CourseBuilder().build();
        StageManager.getInstance().setCourseStage(validCourse);
        validCourse.addStudent(new StudentBuilder().build());

        Student firstPerson = validCourse.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("some remarks").build();
        RemoveCommand.EditStudentDescriptor studentDescriptor = new RemoveCommand.EditStudentDescriptor();

        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_STUDENT, studentDescriptor);
        CommandResult commandResult = removeCommand.execute(model);
        String expectedMessage = String.format(MESSAGE_EDIT_STUDENT_SUCCESS, Messages.format(editedPerson));
        CommandResult expectedResult = new CommandResult(expectedMessage);

        assertEquals(commandResult, expectedResult);
    }

    @Test
    public void equals() {
        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        RemoveCommand removeFirst = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptor);
        RemoveCommand removeSecond = new RemoveCommand(TypicalIndexes.INDEX_SECOND_STUDENT, editStudentDescriptor);

        // same object -> returns true
        assertTrue(removeFirst.equals(removeFirst));

        // same value for Index -> returns true
        RemoveCommand removeFirstCopy = new RemoveCommand(Index.fromZeroBased(0), editStudentDescriptor);
        assertTrue(removeFirst.equals(removeFirstCopy));

        // same value for editStudentDescriptor -> returns true
        RemoveCommand.EditStudentDescriptor editStudentDescriptorCopy = new RemoveCommand.EditStudentDescriptor();
        RemoveCommand removeFirstCopyEsd = new RemoveCommand(
                TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptorCopy);
        assertTrue(removeFirst.equals(removeFirstCopyEsd));

        // different types -> returns false
        assertFalse(removeFirst.equals(1));

        // null -> returns false
        assertFalse(removeFirst.equals(null));

        // different Index -> returns false
        assertFalse(removeFirst.equals(removeSecond));
    }

    @Test
    public void toStringMethod() {
        RemoveCommand.EditStudentDescriptor editStudentDescriptor = new RemoveCommand.EditStudentDescriptor();
        editStudentDescriptor.setPendingQuestion(new PendingQuestion(""));
        RemoveCommand removeCommand = new RemoveCommand(TypicalIndexes.INDEX_FIRST_STUDENT, editStudentDescriptor);
        String expected = RemoveCommand.class.getCanonicalName() + "{index=" + TypicalIndexes.INDEX_FIRST_STUDENT
                + ", editStudentDescriptor=" + editStudentDescriptor.toString() + "}";

        assertEquals(expected, removeCommand.toString());
    }
}
