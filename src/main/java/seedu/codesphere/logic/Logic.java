package seedu.codesphere.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.logic.commands.CommandResult;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.course.Course;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the CourseList.
     *
     * @see seedu.codesphere.model.Model#getCourseList()
     */
    ReadOnlyCourseList getCourseList();

    /** Returns an unmodifiable view of the filtered list of courses */
    ObservableList<Course> getFilteredCourseList();

    /**
     * Returns the user prefs' course list file path.
     */
    Path getCourseListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
