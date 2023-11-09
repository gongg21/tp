package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_2100;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.course.CourseName;
import seedu.address.testutil.EditCourseDescriptorBuilder;


public class EditCourseCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCourseCommand.MESSAGE_USAGE);
    private EditCourseCommandParser parser = new EditCourseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COURSE_NAME_2100, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCourseCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 " + VALID_COURSE_NAME_2100, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + VALID_COURSE_NAME_2100, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid course name
        assertParseFailure(parser, "1 " + INVALID_COURSE_NAME_DESC, CourseName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_editCourse_success() {
        Index targetIndex = INDEX_SECOND_COURSE;
        String userInput = targetIndex.getOneBased() + COURSE_DESC_CS2100;

        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder()
                .withCourseName(VALID_COURSE_NAME_2100).build();
        EditCourseCommand expectedCommand = new EditCourseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput;

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + COURSE_DESC_CS2100 + COURSE_DESC_CS2101;
        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE_NAME));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_COURSE_NAME_DESC + INVALID_COURSE_NAME_DESC;
        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE_NAME));
    }
}
