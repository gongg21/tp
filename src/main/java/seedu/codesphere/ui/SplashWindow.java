package seedu.codesphere.ui;

import javafx.scene.layout.Region;

/**
 * A UI for the splash window
 */
public class SplashWindow extends UiPart<Region> {

    private static final String FXML = "SplashWindow.fxml";

    public SplashWindow() {
        super(FXML);
    }
}
