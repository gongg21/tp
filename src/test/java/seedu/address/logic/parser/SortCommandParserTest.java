package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.Field;
import seedu.address.model.person.SortCriteria;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgsSortByName_success() {
        String userInput = " " + PREFIX_SORT + "name";
        SortCommand expectedCommand = new SortCommand(new SortCriteria(Field.NAME));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsSortByTag_success() {
        String userInput = " " + PREFIX_SORT + "tag";
        SortCommand expectedCommand = new SortCommand(new SortCriteria(Field.TAG));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsWithExtraWhitespace_returnsSortCommand() {
        String userInput = " " + PREFIX_SORT + "  " + "NAME" + "   ";
        SortCommand expectedCommand = new SortCommand(new SortCriteria(Field.NAME));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgsSortCriteria_throwsParseException() {
        String userInput = " " + PREFIX_SORT + "INVALID";
        String expectedMessage = SortCriteria.MESSAGE_CONSTRAINTS_ENUMS;
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidArgsPrefixMissing_throwsParseException() {
        String userInput = " " + "NAME";
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
