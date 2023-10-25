package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalCourses.CS1101S;
import static seedu.address.testutil.TypicalCourses.CS1231S;
import static seedu.address.testutil.TypicalCourses.CS2030S;
import static seedu.address.testutil.TypicalCourses.CS2040S;
import static seedu.address.testutil.TypicalCourses.CS3230;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Student;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class PendingQuestionCommandTest {

    private static final String PENDING_QUESTION_STUB = "Some question";

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    public static List<Course> activateStudent0() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS1101S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent1() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS1231S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent2() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS2030S.addStudent(typicalStudents.get(0));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent3() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS2040S.addStudent(typicalStudents.get(2));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }

    public static List<Course> activateStudent4() {
        List<Student> typicalStudents = TypicalStudents.getTypicalStudents();
        CS3230.addStudent(typicalStudents.get(1));
        return new ArrayList<>(Arrays.asList(CS1101S, CS1231S, CS2030S, CS2040S, CS3230));
    }


    @Test
    public void execute_addPendingQuestionUnfilteredList_success() {
        Course validCourse1 = activateStudent1().get(1);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse1);

        Student firstPerson = validCourse1.getStudentList().getStudent(INDEX_FIRST_STUDENT);
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion(PENDING_QUESTION_STUB).build();

        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(editedPerson.getPendingQuestion().value));

        String expectedMessage = String.format(PendingQuestionCommand
                .MESSAGE_ADD_PENDING_QUESTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        validCourse1.setStudent(firstPerson, editedPerson);
        assertCommandSuccess(pendingQuestionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deletePendingQuestionUnfilteredList_success() {
        Course validCourse2 = activateStudent2().get(2);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse2);
        Student firstPerson = validCourse2.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(firstPerson).withPendingQuestion("").build();

        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(editedPerson.getPendingQuestion().toString()));

        String expectedMessage = String.format(
                pendingQuestionCommand.MESSAGE_DELETE_PENDING_QUESTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        validCourse2.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(pendingQuestionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Course validCourse3 = activateStudent3().get(3);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse3);
        showStudentAtIndex(validCourse3, INDEX_FIRST_STUDENT);
        Student firstPerson = validCourse3.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(validCourse3.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()))
                .withRemark(PENDING_QUESTION_STUB).build();

        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(editedPerson.getPendingQuestion().value));

        String expectedMessage = String.format(
                PendingQuestionCommand.MESSAGE_ADD_PENDING_QUESTION_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        validCourse3.setStudent(firstPerson, editedPerson);

        assertCommandSuccess(pendingQuestionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Course validCourse0 = activateStudent0().get(0);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse0);
        Index outOfBoundIndex = Index.fromOneBased(validCourse0.getFilteredStudentList().size() + 1);
        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(
                outOfBoundIndex, new PendingQuestion(VALID_PENDING_QUESTION_BOB));

        assertCommandFailure(pendingQuestionCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        Course validCourse4 = activateStudent4().get(4);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse4);
        showStudentAtIndex(validCourse4, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertFalse(outOfBoundIndex.getZeroBased() < validCourse4.getStudentList().size());

        PendingQuestionCommand pendingQuestionCommand = new PendingQuestionCommand(
                outOfBoundIndex, new PendingQuestion(VALID_PENDING_QUESTION_BOB));

        assertCommandFailure(pendingQuestionCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PendingQuestionCommand standardCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY));
        final PendingQuestionCommand commandWithSameValues = new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY));
        System.out.println(commandWithSameValues);

        // same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PendingQuestionCommand(INDEX_SECOND_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new PendingQuestionCommand(INDEX_FIRST_STUDENT,
                new PendingQuestion(VALID_PENDING_QUESTION_BOB))));
    }
}
