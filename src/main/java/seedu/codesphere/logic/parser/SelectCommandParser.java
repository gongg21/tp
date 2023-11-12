//@@author gongg21
package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.exceptions.IllegalValueException;
import seedu.codesphere.logic.commands.SelectCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectCommand object
 */
public class SelectCommandParser implements Parser<SelectCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SelectCommand
     * and returns a SelectCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public SelectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new SelectCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE), ive);
        }
    }
}
//@@author gongg21
