package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PENDINGQUESTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PendingQuestionCommand;
import seedu.address.model.person.PendingQuestion;

public class PendingQuestionCommandParserTest {
    private PendingQuestionCommandParser parser = new PendingQuestionCommandParser();
    private final String nonEmptyPendingQuestion = "Some question.";

    @Test
    public void parse_indexSpecified_success() {
        // have pending question
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_PENDINGQUESTION + nonEmptyPendingQuestion;
        PendingQuestionCommand expectedCommand = new PendingQuestionCommand(
                INDEX_FIRST_STUDENT, new PendingQuestion(nonEmptyPendingQuestion));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no pending question
        userInput = targetIndex.getOneBased() + " " + PREFIX_PENDINGQUESTION;
        expectedCommand = new PendingQuestionCommand(INDEX_FIRST_STUDENT, new PendingQuestion(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PendingQuestionCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, PendingQuestionCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser,
                PendingQuestionCommand.COMMAND_WORD + " " + nonEmptyPendingQuestion, expectedMessage);
    }
}
