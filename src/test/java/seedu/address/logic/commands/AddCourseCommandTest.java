package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.CS2100;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;

public class AddCourseCommandTest {

    @Test
    public void constructor_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCourseCommand(null));
    }

    @Test
    public void execute_courseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCourseAdded modelStub = new ModelStubAcceptingCourseAdded();
        Course validCourse = new CourseBuilder().build();

        CommandResult commandResult = new AddCourseCommand(validCourse).execute(modelStub);

        assertEquals(String.format(AddCourseCommand.MESSAGE_SUCCESS, Messages.format(validCourse)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCourse), modelStub.coursesAdded);
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course validCourse = new CourseBuilder().build();
        AddCourseCommand addCourseCommand = new AddCourseCommand(validCourse);
        ModelStub modelStub = new ModelStubWithCourse(validCourse);

        assertThrows(CommandException.class,
                AddCourseCommand.MESSAGE_DUPLICATE_COURSE, () -> addCourseCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Course cs1101s = new CourseBuilder().withCourseName("CS1101S").build();
        Course cs1231s = new CourseBuilder().withCourseName("CS1231S").build();
        AddCourseCommand addCS1101SCommand = new AddCourseCommand(cs1101s);
        AddCourseCommand addCS1231SCommand = new AddCourseCommand(cs1231s);

        // same object -> returns true
        assertTrue(addCS1101SCommand.equals(addCS1101SCommand));

        // same values -> returns true
        AddCourseCommand addCS1101SCommandCopy = new AddCourseCommand(cs1101s);
        assertTrue(addCS1101SCommand.equals(addCS1101SCommandCopy));

        // different types -> returns false
        assertFalse(addCS1101SCommand.equals(1));

        // null -> returns false
        assertFalse(addCS1101SCommand.equals(null));

        // different Student -> returns false
        assertFalse(addCS1101SCommand.equals(addCS1231SCommand));
    }

    @Test
    public void toStringMethod() {
        AddCourseCommand addCourseCommand = new AddCourseCommand(CS2100);
        String expected = AddCourseCommand.class.getCanonicalName() + "{toAdd=" + CS2100 + "}";
        assertEquals(expected, addCourseCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourseList(ReadOnlyCourseList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCourseList getCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCourse(Course target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourse(Course target, Course editedCourse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Student.
     */
    private class ModelStubWithCourse extends ModelStub {
        private final Course course;

        ModelStubWithCourse(Course course) {
            requireNonNull(course);
            this.course = course;
        }

        @Override
        public boolean hasCourse(Course course) {
            requireNonNull(course);
            return this.course.isSameCourse(course);
        }
    }

    /**
     * A Model stub that always accept the Student being added.
     */
    private class ModelStubAcceptingCourseAdded extends ModelStub {
        final ArrayList<Course> coursesAdded = new ArrayList<>();

        @Override
        public boolean hasCourse(Course course) {
            requireNonNull(course);
            return coursesAdded.stream().anyMatch(course::isSameCourse);
        }

        @Override
        public void addCourse(Course course) {
            requireNonNull(course);
            coursesAdded.add(course);
        }

        @Override
        public ReadOnlyCourseList getCourseList() {
            return new CourseList();
        }
    }

}
