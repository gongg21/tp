package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;
import seedu.address.model.person.Student;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.tag.StudentRank;
import seedu.address.testutil.EditCourseDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CLARA = "Clara Ng";

    public static final String VALID_COURSE_NAME_2100 = "CS2100";
    public static final String VALID_COURSE_NAME_2101 = "CS2101";
    public static final String VALID_COURSE_NAME_2102 = "CS2102";

    public static final String VALID_EMAIL_AMY = "e0000000@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "e1111111@u.nus.edu";
    public static final String VALID_EMAIL_CLARA = "e2222222@u.nus.edu";
    public static final String VALID_PENDING_QUESTION_AMY = "How to study more effectively?";
    public static final String VALID_PENDING_QUESTION_BOB = "How to sleep 8 hours a day?";

    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";

    public static final StudentRank VALID_TAG_AVERAGE = StudentRank.AVERAGE;
    public static final StudentRank VALID_TAG_GOOD = StudentRank.GOOD;
    public static final StudentRank VALID_TAG_POOR = StudentRank.POOR;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;

    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;

    public static final String TAG_DESC_AVERAGE = " " + PREFIX_TAG + VALID_TAG_AVERAGE;
    public static final String TAG_DESC_GOOD = " " + PREFIX_TAG + VALID_TAG_GOOD;
    public static final String TAG_DESC_POOR = " " + PREFIX_TAG + VALID_TAG_POOR;

    public static final String PENDING_QUESTION_DESC_AMY = " " + PREFIX_PENDING_QUESTION + VALID_PENDING_QUESTION_AMY;
    public static final String PENDING_QUESTION_DESC_BOB = " " + PREFIX_PENDING_QUESTION + VALID_PENDING_QUESTION_BOB;

    public static final String COURSE_DESC_CS2100 = " " + PREFIX_COURSE_NAME + VALID_COURSE_NAME_2100;
    public static final String COURSE_DESC_CS2101 = " " + PREFIX_COURSE_NAME + VALID_COURSE_NAME_2101;
    public static final String COURSE_DESC_CS2102 = " " + PREFIX_COURSE_NAME + VALID_COURSE_NAME_2102;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    // 'a' not allowed in COURSEs
    public static final String INVALID_COURSE_NAME_DESC = " " + PREFIX_COURSE_NAME + "911CS";
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // invalid email format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // tag must be good, average or poor

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;
    public static final EditCommand.EditStudentDescriptor DESC_CLARA;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withRemark(VALID_REMARK_AMY)
                .withPendingQuestion(VALID_PENDING_QUESTION_AMY).withTag(VALID_TAG_AVERAGE).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).withRemark(VALID_REMARK_BOB)
                .withPendingQuestion(VALID_PENDING_QUESTION_BOB).withTag(VALID_TAG_POOR).build();
        DESC_CLARA = new EditStudentDescriptorBuilder().withName(VALID_NAME_CLARA)
                    .withEmail(VALID_EMAIL_CLARA).withTag(VALID_TAG_GOOD).build();
    }

    public static final EditCourseCommand.EditCourseDescriptor DESC_CS2100;
    public static final EditCourseCommand.EditCourseDescriptor DESC_CS2101;
    public static final EditCourseCommand.EditCourseDescriptor DESC_CS2102;

    static {
        DESC_CS2100 = new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2100).build();
        DESC_CS2101 = new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2101).build();
        DESC_CS2102 = new EditCourseDescriptorBuilder().withCourseName(VALID_COURSE_NAME_2102).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        CourseList expectedCourseList = new CourseList(actualModel.getCourseList());
        List<Course> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCourseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCourseList, actualModel.getCourseList());
        assertEquals(expectedFilteredList, actualModel.getFilteredCourseList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Course course, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < course.getFilteredStudentList().size());

        Student student = course.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        course.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, course.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the course at the given {@code targetIndex} in the
     * {@code model}'s course list.
     */
    public static void showCourseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCourseList().size());

        Course course = model.getFilteredCourseList().get(targetIndex.getZeroBased());
        model.updateFilteredCourseList(
                new CourseNameContainsKeywordsPredicate(Arrays.asList(course.getCourseName().fullCourseName)));

        assertEquals(1, model.getFilteredCourseList().size());
    }

}
