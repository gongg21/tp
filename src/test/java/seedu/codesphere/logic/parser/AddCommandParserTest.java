package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_COURSE_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.codesphere.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.codesphere.logic.commands.CommandTestUtil.TAG_DESC_AVERAGE;
import static seedu.codesphere.logic.commands.CommandTestUtil.TAG_DESC_GOOD;
import static seedu.codesphere.logic.commands.CommandTestUtil.TAG_DESC_POOR;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_POOR;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codesphere.testutil.TypicalStudents.AMY;
import static seedu.codesphere.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.AddCommand;
import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_GOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_GOOD, new AddCommand(expectedStudent));


        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_POOR, VALID_TAG_AVERAGE)
                .build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_POOR + TAG_DESC_AVERAGE,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedStudentString = NAME_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_AVERAGE;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedStudentString + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_EMAIL));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid COURSE
        assertParseFailure(parser, INVALID_COURSE_DESC + validExpectedStudentString,
                Messages.getErrorMessageForDuplicatePrefixes());

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedStudentString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedStudentString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid COURSE
        assertParseFailure(parser, validExpectedStudentString + INVALID_COURSE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes());

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedStudent));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB
                + TAG_DESC_GOOD + TAG_DESC_AVERAGE, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_GOOD + TAG_DESC_AVERAGE, Email.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_AVERAGE + TAG_DESC_GOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
