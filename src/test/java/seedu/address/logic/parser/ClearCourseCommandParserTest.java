package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCourseCommand;

public class ClearCourseCommandParserTest {
    private ClearCourseCommandParser parser = new ClearCourseCommandParser();
    @Test
    public void parse_invalidValue_failure() {
        // no index specified
        assertParseFailure(parser, ClearCourseCommand.COMMAND_WORD + PREAMBLE_NON_EMPTY ,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCourseCommand.MESSAGE_USAGE));
    }
}
