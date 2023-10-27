package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HomeCommand object
 */
public class HomeCommandParser implements Parser<HomeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the HomeCommand
     * and returns a HomeCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public HomeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (argMultimap.getPreamble().isEmpty()) {
            return new HomeCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HomeCommand.MESSAGE_USAGE));
    }
}
