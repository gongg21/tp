package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_COURSE_NAME_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.codesphere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.TAG_DESC_AVERAGE;
import static seedu.codesphere.logic.commands.CommandTestUtil.TAG_DESC_GOOD;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.codesphere.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.EditCommand;
import seedu.codesphere.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;
import seedu.codesphere.model.tag.Tag;
import seedu.codesphere.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_GOOD + TAG_DESC_AVERAGE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_GOOD + TAG_EMPTY + TAG_DESC_AVERAGE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_GOOD + TAG_DESC_AVERAGE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_AVERAGE
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_AVERAGE;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_AVERAGE, VALID_TAG_AVERAGE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags

        userInput = targetIndex.getOneBased() + TAG_DESC_GOOD;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_GOOD).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_COURSE_NAME_DESC;

        // invalid followed by valid
        userInput = targetIndex.getOneBased()+ INVALID_COURSE_NAME_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes());

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY
                + TAG_DESC_GOOD + EMAIL_DESC_AMY + TAG_DESC_GOOD
                + EMAIL_DESC_BOB + TAG_DESC_AVERAGE;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_COURSE_NAME_DESC + INVALID_EMAIL_DESC
                + INVALID_COURSE_NAME_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
