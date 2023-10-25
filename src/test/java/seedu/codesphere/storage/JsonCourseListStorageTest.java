package seedu.codesphere.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalCourses.CS1231S;
import static seedu.codesphere.testutil.TypicalCourses.CS2100;
import static seedu.codesphere.testutil.TypicalCourses.CS2103T;
import static seedu.codesphere.testutil.TypicalCourses.getTypicalCourseList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.codesphere.commons.exceptions.DataLoadingException;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;

public class JsonCourseListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonCourseListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCourseList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCourseList(null));
    }

    private java.util.Optional<ReadOnlyCourseList> readCourseList(String filePath) throws Exception {
        return new JsonCourseListStorage(Paths.get(filePath)).readCourseList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCourseList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCourseList("notJsonFormatCourseList.json"));
    }

    @Test
    public void readCourseList_invalidCourseCourseList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCourseList("invalidCourseCourseList.json"));
    }

    @Test
    public void readCourseList_invalidAndValidCourseList_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCourseList("invalidAndValidCourseList.json"));
    }

    @Test
    public void readAndSaveCourseList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCourseList.json");
        CourseList original = getTypicalCourseList();
        JsonCourseListStorage jsonCourseListStorage = new JsonCourseListStorage(filePath);

        // Save in new file and read back
        jsonCourseListStorage.saveCourseList(original, filePath);
        ReadOnlyCourseList readBack = jsonCourseListStorage.readCourseList(filePath).get();
        assertEquals(original, new CourseList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCourse(CS2100);
        original.removeCourse(CS1231S);
        jsonCourseListStorage.saveCourseList(original, filePath);
        readBack = jsonCourseListStorage.readCourseList(filePath).get();
        assertEquals(original, new CourseList(readBack));

        // Save and read without specifying file path
        original.addCourse(CS2103T);
        jsonCourseListStorage.saveCourseList(original); // file path not specified
        readBack = jsonCourseListStorage.readCourseList().get(); // file path not specified
        assertEquals(original, new CourseList(readBack));

    }

    @Test
    public void saveCourseList_nullCourseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourseList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studentList} at the specified {@code filePath}.
     */
    private void saveCourseList(ReadOnlyCourseList courseList, String filePath) {
        try {
            new JsonCourseListStorage(Paths.get(filePath))
                    .saveCourseList(courseList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCourseList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourseList(new CourseList(), null));
    }
}
