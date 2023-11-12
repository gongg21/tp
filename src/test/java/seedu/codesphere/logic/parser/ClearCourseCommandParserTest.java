//@@author gongg21
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.codesphere.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.codesphere.logic.commands.ClearCourseCommand;

public class ClearCourseCommandParserTest {
    private ClearCourseCommandParser parser = new ClearCourseCommandParser();
    @Test
    public void parse_invalidValue_failure() {
        // no index specified
        assertParseFailure(parser, ClearCourseCommand.COMMAND_WORD + PREAMBLE_NON_EMPTY ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCourseCommand.MESSAGE_USAGE));
    }
}
//@@author gongg21
