package seedu.codesphere.model.tag;

import static seedu.codesphere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.logic.parser.ParserUtil;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalValueException() {
        String invalidTagName = "#invalid";
        assertThrows(IllegalValueException.class, () -> ParserUtil.parseTag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}
