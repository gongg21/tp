package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2101;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.testutil.EditCourseDescriptorBuilder;

public class EditCourseDescriptorTest {

    @Test
    public void equals() {
        // same course name -> returns true
        EditCourseDescriptor descriptorWithSameValues = new EditCourseDescriptor(DESC_CS2100);
        assertTrue(DESC_CS2100.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2100.equals(DESC_CS2100));

        // null -> returns false
        assertFalse(DESC_CS2100.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2100.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2100.equals(DESC_CS2101));

        // different course name -> returns false
        EditCourseDescriptor editedCS2101 = new EditCourseDescriptorBuilder(DESC_CS2101).build();
        assertFalse(DESC_CS2100.equals(editedCS2101));

    }

    @Test
    public void toStringMethod() {
        EditCourseDescriptor editCourseDescriptor = new EditCourseDescriptor();
        String expected = EditCourseDescriptor.class.getCanonicalName() + "{course name="
                + editCourseDescriptor.getCourseName().orElse(null) + "}";
        assertEquals(expected, editCourseDescriptor.toString());
    }
}
