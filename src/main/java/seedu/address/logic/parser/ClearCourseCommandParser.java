package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ClearCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCourseCommand object
 */
public class ClearCourseCommandParser implements Parser<ClearCourseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearCourseCommand
     * and returns a ClearCourseCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public ClearCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (argMultimap.getPreamble().isEmpty()) {
            return new ClearCourseCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCourseCommand.MESSAGE_USAGE));
    }
}
