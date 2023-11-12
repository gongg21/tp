package seedu.codesphere.logic.parser;

//@@author gongg21
import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_COURSE_NAME;

import java.util.stream.Stream;

import seedu.codesphere.logic.commands.AddCourseCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;

/**
 * Parses input arguments and creates a new AddCourseCommand object
 */
public class AddCourseCommandParser implements Parser<AddCourseCommand> {

    /**
     * Parses the given {@code String} in the context of the AddCourseCommand
     * and returns an AddCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COURSE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_COURSE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCourseCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COURSE_NAME);
        CourseName courseName = ParserUtil.parseCourseName(argMultimap.getValue(PREFIX_COURSE_NAME).get());
        Course course = new Course(courseName);

        return new AddCourseCommand(course);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
//@@author gongg21
