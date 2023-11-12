//@@author gongg21
package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.commands.DeleteCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCourseCommandParser implements Parser<DeleteCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCourseCommand
     * and returns a DeleteCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCourseCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCourseCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCourseCommand.MESSAGE_USAGE), pe);
        }
    }
}
//@@author gongg21
