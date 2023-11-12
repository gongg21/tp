//@@author gongg21
package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_COURSE_NAME;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.commands.EditCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCourseCommandParser implements Parser<EditCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCourseCommand
     * and returns an EditCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCourseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COURSE_NAME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCourseCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COURSE_NAME);

        EditCourseCommand.EditCourseDescriptor editCourseDescriptor = new EditCourseCommand.EditCourseDescriptor();

        if (argMultimap.getValue(PREFIX_COURSE_NAME).isPresent()) {
            editCourseDescriptor.setCourseName(
                    ParserUtil.parseCourseName(argMultimap.getValue(PREFIX_COURSE_NAME).get()));
        }

        if (!editCourseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCourseCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCourseCommand(index, editCourseDescriptor);
    }
}
//@@author gongg21
