package seedu.codesphere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.model.Model.PREDICATE_SHOW_ALL_COURSES;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.CS2100;
import static seedu.codesphere.testutil.TypicalCourses.CS2103T;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.testutil.CourseListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new CourseList(), new CourseList(modelManager.getCourseList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasCourse_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCourse(null));
    }

    @Test
    public void hasCourse_courseNotInCourseList_returnsFalse() {
        assertFalse(modelManager.hasCourse(CS2100));
    }

    @Test
    public void hasCourse_courseInCourseList_returnsTrue() {
        modelManager.addCourse(CS2100);
        assertTrue(modelManager.hasCourse(CS2100));
    }

    @Test
    public void getFilteredCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCourseList().remove(0));
    }

    @Test
    public void equals() {
        CourseList courseList = new CourseListBuilder().withCourse(CS2100).withCourse(CS2103T).build();
        CourseList differentCourseList = new CourseList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(courseList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(courseList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different courseList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentCourseList, userPrefs)));

        // different filteredList -> returns false
        /* String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(courseList, userPrefs))); */

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(courseList, differentUserPrefs)));
    }
}
