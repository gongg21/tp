package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2101;
import static seedu.address.testutil.TypicalCourses.CS2100;
import static seedu.address.testutil.TypicalCourses.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CourseBuilder;

public class CourseTest {

    @Test
    public void isSameCourse() {
        // same object -> returns true
        assertTrue(CS2100.isSameCourse(CS2100));

        // null -> returns false
        assertFalse(CS2100.isSameCourse(null));

        // different course name -> returns false
        assertFalse(CS2100.isSameCourse(CS2103T));

        // course name differs in case -> returns false
        Course editedCS2100 = new CourseBuilder(CS2100).withCourseName(VALID_COURSE_NAME_2100.toLowerCase()).build();
        assertFalse(CS2100.isSameCourse(editedCS2100));

        // course name has trailing spaces -> returns false
        String courseNameWithTrailingSpaces = VALID_COURSE_NAME_2100 + " ";
        editedCS2100 = new CourseBuilder(CS2100).withCourseName(courseNameWithTrailingSpaces).build();
        assertFalse(CS2100.isSameCourse(editedCS2100));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Course CS2100Copy = new CourseBuilder(CS2100).build();
        assertTrue(CS2100.equals(CS2100Copy));

        // same object -> returns true
        assertTrue(CS2100.equals(CS2100));

        // null -> returns false
        assertFalse(CS2100.equals(null));

        // different type -> returns false
        assertFalse(CS2100.equals(5));

        // different Student -> returns false
        assertFalse(CS2100.equals(CS2103T));

        // different course name name -> returns false
        Course editedCS2100 = new CourseBuilder(CS2100).withCourseName(VALID_COURSE_NAME_2101).build();
        assertFalse(CS2100.equals(editedCS2100));
    }

    @Test
    public void toStringMethod() {
        String expected = Course.class.getCanonicalName() + "{course name=" + CS2100.getCourseName() + "}";

        assertEquals(expected, CS2100.toString());
    }
}
