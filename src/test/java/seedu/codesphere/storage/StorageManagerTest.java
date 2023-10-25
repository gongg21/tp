package seedu.codesphere.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.UserPrefs;


public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCourseListStorage courseListStorage = new JsonCourseListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(courseListStorage, userPrefsStorage);
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

}
