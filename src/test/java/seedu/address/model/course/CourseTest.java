package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2101;
import static seedu.address.testutil.TypicalCourses.CS2100;
import static seedu.address.testutil.TypicalCourses.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.TypicalStudents;

public class CourseTest {

    @Test
    public void isSameCourse() {
        // same object -> returns true
        assertTrue(CS2100.isSameCourse(CS2100));

        // null -> returns false
        assertFalse(CS2100.isSameCourse(null));

        // different course name -> returns false
        assertFalse(CS2100.isSameCourse(CS2103T));

        // course name differs in case -> returns true
        Course editedCS2100 = new CourseBuilder(CS2100).withCourseName(VALID_COURSE_NAME_2100.toLowerCase()).build();
        assertTrue(CS2100.isSameCourse(editedCS2100));

        // same name but different students -> returns true
        Course newCS2100 = new CourseBuilder(CS2100).build();
        newCS2100.addStudent(TypicalStudents.ALICE);
        Course differentCS2100 = new CourseBuilder(CS2100).build();
        differentCS2100.addStudent(TypicalStudents.BOB);
        assertTrue(newCS2100.isSameCourse(differentCS2100));
    }

    @Test
    public void invalidCourseName_throwsIllegalArgumentException() {
        // course name has trailing spaces -> returns true
        String courseNameWithTrailingSpaces = VALID_COURSE_NAME_2101 + " ";
        assertThrows(IllegalArgumentException.class, () ->
                        new CourseBuilder(CS2100).withCourseName(courseNameWithTrailingSpaces).build(),
                "Course code should contain a two or three letter prefix, a four digit course code,"
                        + "and an optional one letter suffix");
    }

    @Test
    public void equals() {
        // same values -> returns true
        Course cs2100copy = new CourseBuilder(CS2100).build();
        assertTrue(CS2100.equals(cs2100copy));

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
