package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.CS1101S;
import static seedu.address.testutil.TypicalCourses.CS1231S;
import static seedu.address.testutil.TypicalCourses.CS2030S;
import static seedu.address.testutil.TypicalCourses.CS2040S;
import static seedu.address.testutil.TypicalCourses.CS3230;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.StageManager;
import seedu.address.model.CourseList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Student;
import seedu.address.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
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


    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        Course validCourse0 = activateStudent0().get(0);
        validCourse0.addStudent(BENSON);
        validCourse0.addStudent(CARL);
        validCourse0.addStudent(DANIEL);
        validCourse0.addStudent(ELLE);
        validCourse0.addStudent(FIONA);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse0);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        validCourse0.updateFilteredStudentList(predicate);
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), validCourse0.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        Course validCourse1 = activateStudent1().get(1);
        validCourse1.addStudent(BENSON);
        validCourse1.addStudent(CARL);
        validCourse1.addStudent(DANIEL);
        validCourse1.addStudent(ELLE);
        validCourse1.addStudent(FIONA);
        StageManager stageManager = StageManager.getCurrent();
        stageManager.setCourseStage(validCourse1);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        validCourse1.updateFilteredStudentList(predicate);
        Model expectedModel = new ModelManager(new CourseList(model.getCourseList()), new UserPrefs());
        System.out.println(expectedModel.equals(model));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), validCourse1.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
