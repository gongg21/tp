package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.HomeCommand;


public class HomeCommandParserTest {

    private HomeCommandParser parser = new HomeCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        // no index specified
        assertParseFailure(parser, HomeCommand.COMMAND_WORD + PREAMBLE_NON_EMPTY ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeCommand.MESSAGE_USAGE));
    }
}
