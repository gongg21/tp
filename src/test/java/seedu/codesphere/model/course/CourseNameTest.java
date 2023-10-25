package seedu.codesphere.model.course;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseName(invalidName));
    }

    @Test
    public void isValidCourseName() {
        // null Course number
        assertThrows(NullPointerException.class, () -> CourseName.isValidCourseName(null));

        // invalid Course names
        assertFalse(CourseName.isValidCourseName("")); // empty string
        assertFalse(CourseName.isValidCourseName(" ")); // spaces only
        assertFalse(CourseName.isValidCourseName("91")); // less than 3 numbers
        assertFalse(CourseName.isValidCourseName("9011p041")); // alphabets within digits
        assertFalse(CourseName.isValidCourseName("9312 1534")); // spaces within digits

        // valid Course names
        assertTrue(CourseName.isValidCourseName("Course")); // non-numeric
        assertTrue(CourseName.isValidCourseName("CS2103T"));
        assertTrue(CourseName.isValidCourseName("CS2101"));
        assertTrue(CourseName.isValidCourseName("MA2001"));
    }

    @Test
    public void equals() {
        CourseName courseName = new CourseName("CS2101");

        // same values -> returns true
        assertTrue(courseName.equals(new CourseName("CS2101")));

        // same object -> returns true
        assertTrue(courseName.equals(courseName));

        // null -> returns false
        assertFalse(courseName.equals(null));

        // different types -> returns false
        assertFalse(courseName.equals(5.0f));

        // different values -> returns false
        assertFalse(courseName.equals(new CourseName("CS2103T")));
    }
}
