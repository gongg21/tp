package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDINGQUESTION;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.PendingQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PendingQuestion;

/**
 * Parses input arguments and creates a new {@code PendingQuestionCommand} object
 */
public class PendingQuestionCommandParser implements Parser<PendingQuestionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code PendingQuestionCommand}
     * and returns a {@code PendingQuestionCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PendingQuestionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PENDINGQUESTION);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            PendingQuestionCommand.MESSAGE_USAGE), ive);
        }

        String pendingQuestion = argMultimap.getValue(PREFIX_PENDINGQUESTION).orElse("");

        return new PendingQuestionCommand(index, new PendingQuestion(pendingQuestion));
    }
}
