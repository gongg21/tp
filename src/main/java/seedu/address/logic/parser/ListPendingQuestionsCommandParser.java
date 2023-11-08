package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;

import java.util.stream.Stream;

import seedu.address.logic.commands.ListPendingQuestionsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.AllPendingQuestionPredicate;

/**
 * Parses input arguments and creates a new ListPendingQuestionsCommand object
 */
public class ListPendingQuestionsCommandParser implements Parser<ListPendingQuestionsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListPendingQuestionsCommand
     * and returns a ListPendingQuestionsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListPendingQuestionsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PENDING_QUESTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_PENDING_QUESTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPendingQuestionsCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PENDING_QUESTION);

        String pendingQuestion = argMultimap.getValue(PREFIX_PENDING_QUESTION).orElse("");
        if (!pendingQuestion.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPendingQuestionsCommand.MESSAGE_USAGE));
        }

        return new ListPendingQuestionsCommand(new AllPendingQuestionPredicate());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
