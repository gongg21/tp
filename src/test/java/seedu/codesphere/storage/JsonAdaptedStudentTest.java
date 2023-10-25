package seedu.codesphere.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.codesphere.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_PENDING_QUESTION = BENSON.getPendingQuestion().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_EMAIL, VALID_REMARK, VALID_PENDING_QUESTION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_EMAIL,
                VALID_REMARK, VALID_PENDING_QUESTION, VALID_TAGS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_EMAIL, VALID_REMARK, VALID_PENDING_QUESTION,
                        VALID_TAGS);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_REMARK, VALID_PENDING_QUESTION,
                VALID_TAGS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_EMAIL, VALID_REMARK, VALID_PENDING_QUESTION,
                        invalidTags);

        assertThrows(IllegalValueException.class, student::toModelType);
    }

}
