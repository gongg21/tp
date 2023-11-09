package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.StageManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.Student;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());
    private Course course = model.getFilteredCourseList().get(0);

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index targetIndex = Index.fromZeroBased(0);
        Student student = course.getStudentList().getStudent(targetIndex);
        Student editedStudent = new StudentBuilder(student).withName(VALID_NAME_BOB).withTag(VALID_TAG_AVERAGE).build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);
        course.setStudent(student, editedStudent);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS,
                Messages.format(editedStudent));

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.setCourse(model.getFilteredCourseList().get(0), course);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index targetIndex = Index.fromZeroBased(0);
        Student student = course.getStudentList().getStudent(targetIndex);
        Student editedStudent = new StudentBuilder(student).build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);
        course.setStudent(student, editedStudent);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS,
                Messages.format(editedStudent));

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.setCourse(model.getFilteredCourseList().get(0), course);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index targetIndex = Index.fromZeroBased(0);
        Student student = course.getStudentList().getStudent(targetIndex);
        course.updateFilteredStudentList(new NameContainsKeywordsPredicate(Collections.singletonList(student
                .getName().fullName)));
        showStudentAtIndex(course, INDEX_FIRST_STUDENT);

        Student editedStudent = new StudentBuilder(student).withName(VALID_NAME_BOB).build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);
        course.setStudent(student, editedStudent);
        course.resetFilteredStudentList();

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STUDENT_SUCCESS,
                Messages.format(editedStudent));

        Model expectedModel = new ModelManager(model.getCourseList(), new UserPrefs());
        expectedModel.setCourse(model.getFilteredCourseList().get(0), course);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index secondIndex = Index.fromZeroBased(1);
        Student student = course.getStudentList().getStudent(secondIndex);
        course.updateFilteredStudentList(new NameContainsKeywordsPredicate(Collections.singletonList(student
                .getName().fullName)));
        showStudentAtIndex(course, INDEX_FIRST_STUDENT);

        Index firstIndex = Index.fromZeroBased(0);
        // edit Student in filtered list into a duplicate in student list
        Student studentInList = course.getStudentList().getStudent(firstIndex);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STUDENT,
                new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index outOfBoundIndex = Index.fromOneBased(course.getStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of student list
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setCourseStage(course);

        Index targetIndex = Index.fromZeroBased(0);
        Student student = course.getStudentList().getStudent(targetIndex);
        course.updateFilteredStudentList(new NameContainsKeywordsPredicate(Collections.singletonList(student
                .getName().fullName)));
        showStudentAtIndex(course, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = Index.fromOneBased(course.getFilteredStudentList().size() + 1);
        // ensures that outOfBoundIndex is still in bounds of student list
        assertTrue(outOfBoundIndex.getZeroBased() < course.getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STUDENT, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_STUDENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_STUDENT, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        EditCommand editCommand = new EditCommand(index, editStudentDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editStudentDescriptor="
                + editStudentDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
