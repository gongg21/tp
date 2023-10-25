package seedu.codesphere.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.codesphere.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.commons.util.JsonUtil;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.testutil.TypicalCourses;

public class JsonSerializableCourseListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCourseListTest");
    private static final Path TYPICAL_COURSE_FILE = TEST_DATA_FOLDER.resolve("typicalCoursesCourseList.json");
    private static final Path INVALID_COURSE_FILE = TEST_DATA_FOLDER.resolve("invalidCourseCourseList.json");
    private static final Path DUPLICATE_COURSE_FILE = TEST_DATA_FOLDER.resolve("duplicateCoursesCourseList.json");

    @Test
    public void toModelType_typicalCourseFile_success() throws Exception {
        JsonSerializableCourseList dataFromFile = JsonUtil.readJsonFile(TYPICAL_COURSE_FILE,
                JsonSerializableCourseList.class).get();
        CourseList courseListFromFile = dataFromFile.toModelType();
        CourseList typicalCourseList = TypicalCourses.getTypicalCourseList();
        assertEquals(courseListFromFile, typicalCourseList);
    }

    @Test
    public void toModelType_invalidCourseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCourseList dataFromFile = JsonUtil.readJsonFile(INVALID_COURSE_FILE,
                JsonSerializableCourseList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCourses_throwsIllegalValueException() throws Exception {
        JsonSerializableCourseList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COURSE_FILE,
                JsonSerializableCourseList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCourseList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
