package seedu.codesphere.model;

import java.nio.file.Path;

import seedu.codesphere.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudentListFilePath();

}
