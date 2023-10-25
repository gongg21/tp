package seedu.codesphere.model;

import static java.util.Objects.requireNonNull;
import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.codesphere.commons.core.GuiSettings;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.model.course.Course;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final CourseList courseList;
    private final UserPrefs userPrefs;
    private final FilteredList<Course> filteredCourses;

    /**
     * Initializes a ModelManager with the given CourseList and userPrefs.
     */
    public ModelManager(ReadOnlyCourseList courseList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(courseList, userPrefs);

        logger.fine("Initializing with course list: " + courseList + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.courseList = new CourseList(courseList);
        filteredCourses = new FilteredList<>(this.courseList.getCourseList());
    }

    public ModelManager() {
        this(new CourseList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getStudentListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== CodeSphere ================================================================================

    @Override
    public void setCourseList(ReadOnlyCourseList courseList) {
        this.courseList.resetData(courseList);
    }

    @Override
    public ReadOnlyCourseList getCourseList() {
        return courseList;
    }

    @Override
    public boolean hasCourse(Course course) {
        requireNonNull(course);
        return courseList.hasCourse(course);
    }

    @Override
    public void deleteCourse(Course target) {
        courseList.removeCourse(target);
    }

    @Override
    public void addCourse(Course course) {
        courseList.addCourse(course);
        updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
    }

    @Override
    public void setCourse(Course target, Course editedCourse) {
        requireAllNonNull(target, editedCourse);

        courseList.setCourse(target, editedCourse);
    }

    //=========== Filtered Course List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Course} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Course> getFilteredCourseList() {
        return filteredCourses;
    }

    @Override
    public void updateFilteredCourseList(Predicate<Course> predicate) {
        requireNonNull(predicate);
        filteredCourses.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return courseList.equals(otherModelManager.courseList)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCourses.equals(otherModelManager.filteredCourses);
    }
}
