package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PENDING_QUESTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_AVERAGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.*;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy")));
        assertParseSuccess(parser, " n/Amy", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " n/Amy ", expectedFindCommand);
    }

    @Test
    public void parse_validTag_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagFilterPredicate(Arrays.asList(VALID_TAG_AVERAGE.toString())));

        assertParseSuccess(parser, TAG_DESC_AVERAGE, expectedFindCommand);

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TAG_DESC_AVERAGE, expectedFindCommand);
    }

    @Test
    public void parse_validPQ_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PqContainsKeywordsPredicate(Arrays.asList(VALID_PENDING_QUESTION_AMY)));
        assertParseSuccess(parser, PENDING_QUESTION_DESC_AMY, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PENDING_QUESTION_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validRemark_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new RemarkContainsKeywordsPredicate(Arrays.asList(VALID_REMARK_AMY)));
        assertParseSuccess(parser, REMARK_DESC_AMY, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + REMARK_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_validEmail_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(VALID_EMAIL_AMY)));
        assertParseSuccess(parser, EMAIL_DESC_AMY, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EMAIL_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_repeatedPrefixValue_failure() {

        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + EMAIL_DESC_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple tags
        assertParseFailure(parser, TAG_DESC_AVERAGE + TAG_DESC_AVERAGE,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));

        assertParseFailure(parser, EMAIL_DESC_AMY + EMAIL_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
    }

    @Test
    public void parse_invalidMultiplePrefix_failure() {

        // multiple whitespaces between keywords
        assertParseFailure(parser, EMAIL_DESC_AMY + NAME_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
