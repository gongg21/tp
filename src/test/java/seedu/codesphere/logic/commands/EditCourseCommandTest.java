package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2100;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCourseAtIndex;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.EditCourseDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCourseCommand.
 */
public class EditCourseCommandTest {

    private Model model = new ModelManager(getTypicalCourseList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Course editedCourse = new CourseBuilder().build();
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(editedCourse).build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_STUDENT, descriptor);

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        expectedModel.setCourse(model.getFilteredCourseList().get(0), editedCourse);

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_STUDENT, new EditCourseDescriptor());
        Course editedCourse = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);

        Course courseInFilteredList = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Course editedCourse = new CourseBuilder(courseInFilteredList).withCourseName(VALID_COURSE_NAME_2100).build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_STUDENT,
                new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2100).build());

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        expectedModel.setCourse(model.getFilteredCourseList().get(0), editedCourse);

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCourseUnfilteredList_failure() {
        Course firstCourse = model.getFilteredCourseList().get(INDEX_FIRST_STUDENT.getZeroBased());
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(firstCourse).build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_SECOND_STUDENT, descriptor);

        assertCommandFailure(editCourseCommand, model, EditCourseCommand.MESSAGE_DUPLICATE_COURSE);
    }

    @Test
    public void execute_duplicateCourseFilteredList_failure() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);

        // edit Course in filtered list into a duplicate in course list
        Course courseInList = model.getCourseList().getCourseList().get(INDEX_SECOND_STUDENT.getZeroBased());
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_STUDENT,
                new EditCourseDescriptorBuilder(courseInList).build());

        assertCommandFailure(editCourseCommand, model, EditCourseCommand.MESSAGE_DUPLICATE_COURSE);
    }

    @Test
    public void execute_invalidCourseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseList().size() + 1);
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2100).build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCourseCommand, model, Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of course list
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showCourseAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of course list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCourseList().getCourseList().size());

        EditCourseCommand editCourseCommand = new EditCourseCommand(outOfBoundIndex,
                new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2100).build());

        assertCommandFailure(editCourseCommand, model, Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCourseCommand standardCommand = new EditCourseCommand(INDEX_FIRST_STUDENT, DESC_CS2100);

        // same values -> returns true
        EditCourseDescriptor copyDescriptor = new EditCourseDescriptor(DESC_CS2100);
        EditCourseCommand commandWithSameValues = new EditCourseCommand(INDEX_FIRST_STUDENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCourseCommand(INDEX_SECOND_STUDENT, DESC_CS2100)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCourseCommand(INDEX_FIRST_STUDENT, DESC_CS2100)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCourseDescriptor editCourseDescriptor = new EditCourseDescriptor();
        EditCourseCommand editCourseCommand = new EditCourseCommand(index, editCourseDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editCourseDescriptor="
                + editCourseDescriptor + "}";
        assertEquals(expected, editCourseCommand.toString());
    }

}
