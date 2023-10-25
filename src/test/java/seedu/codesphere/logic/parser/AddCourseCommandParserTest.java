package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.commands.CommandTestUtil.*;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codesphere.testutil.TypicalCourses.CS2100;

import org.junit.jupiter.api.Test;
import seedu.codesphere.logic.commands.AddCourseCommand;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.testutil.CourseBuilder;

public class AddCourseCommandParserTest {
    private AddCourseCommandParser parser = new AddCourseCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Course expectedCourse = new CourseBuilder(CS2100).build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COURSE_DESC_CS2100,
                new AddCourseCommand(expectedCourse));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCourseCommand.MESSAGE_USAGE);

        // missing prefix
        assertParseFailure(parser, VALID_COURSE_NAME_2100, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // missing prefix
        assertParseFailure(parser, INVALID_COURSE_NAME_DESC, CourseName.MESSAGE_CONSTRAINTS);
    }
}
