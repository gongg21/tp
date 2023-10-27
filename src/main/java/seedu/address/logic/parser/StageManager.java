package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.model.course.Course;

/**
 * This class manages the stages of the application.
 */
public class StageManager {
    private static StageManager current;
    private static Stages currentStage = Stages.HOME;
    private static Course selectedCourse = null;

    private StageManager() {
    }

    public static StageManager getCurrent() {
        if (current == null) {
            current = new StageManager();
            return current;
        }
        return current;
    }

    public static Stages getStage() {
        return currentStage;
    }

    public static Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setCourseStage(Course selectedCourse) {
        requireNonNull(selectedCourse);
        StageManager.currentStage = Stages.COURSE;
        StageManager.selectedCourse = selectedCourse;
    }

    public void setHomeStage() {
        StageManager.currentStage = Stages.HOME;
        StageManager.selectedCourse = null;
    }

    public Course getCurrentCourse() {
        return selectedCourse;
    }

}
