package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.CS1101S;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCourseCommand;
import seedu.address.logic.commands.ClearCourseCommand;
import seedu.address.logic.commands.DeleteCourseCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCourseCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HomeCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.ResetCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Student;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.CourseUtil;
import seedu.address.testutil.EditCourseDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.StudentUtil;

public class CodeSphereParserTest {

    private final CodeSphereParser parser = new CodeSphereParser();

    @Test
    public void parseCommand_addCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();

        Course course = new CourseBuilder().build();
        AddCourseCommand command = (AddCourseCommand) parser.parseCommand(CourseUtil.getAddCourseCommand(course));
        assertEquals(new AddCourseCommand(course), command);
    }

    @Test
    public void parseCommand_clearCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ClearCourseCommand.COMMAND_WORD) instanceof ClearCourseCommand);
    }

    @Test
    public void parseCommand_deleteCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        DeleteCourseCommand command = (DeleteCourseCommand) parser.parseCommand(
                DeleteCourseCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCourseCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_editCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        Course course = new CourseBuilder().build();
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(course).build();
        EditCourseCommand command = (EditCourseCommand) parser.parseCommand(
                EditCourseCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased() + " "
                        + CourseUtil.getEditCourseDescriptorDetails(descriptor));
        assertEquals(new EditCourseCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        Student student = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_STUDENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findCourse() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCourseCommand command = (FindCourseCommand) parser.parseCommand(
                FindCourseCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCourseCommand(new CourseNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        List<String> keywords = Arrays.asList("foo");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_resetCourses() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(ResetCourseCommand.COMMAND_WORD) instanceof ResetCourseCommand);
    }

    @Test
    public void parseCommand_reset() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_home() throws Exception {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertTrue(parser.parseCommand(HomeCommand.COMMAND_WORD) instanceof HomeCommand);

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_STUDENT, remark), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));

    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        // set HOME stage
        StageManager.getInstance().setHomeStage();
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));

        // set COURSE stage
        StageManager.getInstance().setCourseStage(CS1101S);
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));

    }
}
