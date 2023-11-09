package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCourseCommand;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;

public class FindCourseCommandParserTest {

    private FindCourseCommandParser parser = new FindCourseCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCourseCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCourseCommand expectedFindCommand =
                new FindCourseCommand(new CourseNameContainsKeywordsPredicate(Arrays.asList("CS", "ST")));
        assertParseSuccess(parser, "CS ST", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS \n \t ST  \t", expectedFindCommand);
    }

}
