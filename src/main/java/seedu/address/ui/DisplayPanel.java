package seedu.address.ui;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import seedu.address.logic.Logic;
import seedu.address.logic.parser.StageManager;
import seedu.address.logic.parser.Stages;
import seedu.address.model.course.Course;

/**
 * A UI for the center display panel that displays the splash panel, course list and combined list.
 */
public class DisplayPanel extends UiPart<Region> {

    private static final String FXML = "DisplayPanel.fxml";

    private SplashPanel splashPanel;
    private CourseListPanel courseListPanel;
    private CombinedPanel combinedPanel;

    private Logic logic;
    private StageManager stageManager = StageManager.getInstance();

    @FXML
    private StackPane panelPlaceholder;

    /**
     * Creates a {@code DisplayPanel} with the given {@code Logic}.
     */
    public DisplayPanel(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    /**
     * Loads up the start sequence into the display panel.
     * Consists of the splash panel fade in + fade out, then displays whatever is meant to be on the panel
     */
    void loadStartSequence() {
        splashPanel = new SplashPanel();
        panelPlaceholder.getChildren().add(splashPanel.getRoot());

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), panelPlaceholder);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), panelPlaceholder);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeIn.play();
        fadeIn.setOnFinished(fadein -> {
            fadeOut.play();
            fadeOut.setOnFinished(fadeout -> {
                panelPlaceholder.setOpacity(1);
                if (stageManager.getStage() == Stages.HOME) {
                    loadCourseListPanel();
                } else if (stageManager.getStage() == Stages.COURSE) {
                    loadCombinedPanel();
                }
            });
        });
    }

    void loadCourseListPanel() {
        panelPlaceholder.getChildren().clear();
        courseListPanel = new CourseListPanel(logic.getFilteredCourseList());
        panelPlaceholder.getChildren().add(courseListPanel.getRoot());
    }

    void loadCombinedPanel() {
        panelPlaceholder.getChildren().clear();
        combinedPanel = new CombinedPanel(logic.getFilteredCourseList(),
                stageManager.getSelectedCourse().getFilteredStudentList());
        panelPlaceholder.getChildren().add(combinedPanel.getRoot());
    }

}
