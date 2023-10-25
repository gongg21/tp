package seedu.codesphere.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.codesphere.commons.exceptions.DataLoadingException;
import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;

/**
 * Represents a storage for {@link CourseList}.
 */
public interface CourseListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCourseListFilePath();

    /**
     * Returns CodeSphere data as a {@link ReadOnlyCourseList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyCourseList> readCourseList() throws DataLoadingException;

    /**
     * @see #getCourseListFilePath() ()
     */
    Optional<ReadOnlyCourseList> readCourseList(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyCourseList} to the storage.
     * @param courseList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCourseList(ReadOnlyCourseList courseList) throws IOException;

    /**
     * @see #saveCourseList(ReadOnlyCourseList) (ReadOnlyCourseList)
     */
    void saveCourseList(ReadOnlyCourseList courseList, Path filePath) throws IOException;

}
