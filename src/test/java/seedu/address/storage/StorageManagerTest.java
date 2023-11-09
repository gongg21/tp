package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.CourseList;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCourseListStorage courseListStorage = new JsonCourseListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        InputStorage inputStorage = new InputHistory();
        storageManager = new StorageManager(courseListStorage, userPrefsStorage, inputStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void courseListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCourseListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCourseListStorageTest} class.
         */
        CourseList original = getTypicalCourseList();
        storageManager.saveCourseList(original);
        ReadOnlyCourseList retrieved = storageManager.readCourseList().get();
        assertEquals(original, new CourseList(retrieved));
    }

    @Test
    public void getCourseListFilePath() {
        assertNotNull(storageManager.getCourseListFilePath());
    }

    @Test
    public void alwaysReturnLatestInput() {
        storageManager.addValidInput("TRUE_INPUT");
        assertEquals(new Pair<>(true, "TRUE_INPUT"), storageManager.previousInput());
        storageManager.addInvalidInput("FALSE_INPUT");
        assertEquals(new Pair<>(false, "FALSE_INPUT"), storageManager.previousInput());
    }

    @Test
    public void previousInput() {
        storageManager.addValidInput("INPUT_1");
        storageManager.addValidInput("INPUT_2");
        storageManager.addValidInput("INPUT_3");
        assertEquals(new Pair<>(true, "INPUT_3"), storageManager.previousInput());
        assertEquals(new Pair<>(true, "INPUT_2"), storageManager.previousInput());
        assertEquals(new Pair<>(true, "INPUT_1"), storageManager.previousInput());
    }

    @Test
    public void getInputIsEmpty() {
        storageManager.addValidInput("INPUT_1");
        assertEquals(new Pair<>(true, ""), storageManager.getInput());
    }

}
