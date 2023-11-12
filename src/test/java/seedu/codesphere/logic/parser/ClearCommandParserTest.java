//@@author gongg21
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.ClearCommand;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();
    @Test
    public void parse_invalidValue_failure() {
        // no index specified
        assertParseFailure(parser, ClearCommand.COMMAND_WORD + PREAMBLE_NON_EMPTY ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }
}
//@@author gongg21
