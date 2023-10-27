package seedu.address.ui;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import seedu.address.model.course.Course;
import seedu.address.model.person.Student;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class DisplayPanel extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";

    private SplashPanel splashPanel;
    private CourseListPanel courseListPanel;
    private CombinedPanel combinedPanel;

    @FXML
    private StackPane panelPlaceholder;

    /**
     * Creates a {@code DisplayPanel} with the given {@code ObservableList<Course>}.
     */
    public DisplayPanel() {
        super(FXML);
    }

    void loadStartSequence(ObservableList<Course> courseList) {
        splashPanel = new SplashPanel();
        panelPlaceholder.getChildren().add(splashPanel.getRoot());

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), panelPlaceholder);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), panelPlaceholder);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeIn.play();
        fadeIn.setOnFinished(fadein -> {
            fadeOut.play();
            fadeOut.setOnFinished(fadeout -> {
                panelPlaceholder.setOpacity(1);
                loadCourseListPanel(courseList);
            });
        });

    }

    void loadCourseListPanel(ObservableList<Course> courseList) {
        panelPlaceholder.getChildren().clear();
        courseListPanel = new CourseListPanel(courseList);
        panelPlaceholder.getChildren().add(courseListPanel.getRoot());
    }

    void loadCombinedPanel(ObservableList<Course> courseList,
                           ObservableList<Student> studentList) {
        panelPlaceholder.getChildren().clear();
        combinedPanel = new CombinedPanel(courseList, studentList);
        panelPlaceholder.getChildren().add(combinedPanel.getRoot());
    }

}
