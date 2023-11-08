package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Student;

/**
 * A UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private Label pendingQuestion;
    @FXML
    private FlowPane tag;
    @FXML
    private Pane remarkPane;
    @FXML
    private Pane pqPane;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        email.setText(student.getEmail().value);
        String studentTag = student.getTag().ranking.toString();
        Label label = new Label(studentTag);
        switch (studentTag) {
        case ("GOOD"):
            label.setStyle("-fx-background-color: #CAF2C2");
            break;
        case ("AVERAGE"):
            label.setStyle("-fx-background-color: #FFF8B8");
            break;
        case ("POOR"):
            label.setStyle("-fx-background-color: #FFD6C9");
            break;
        default:
            label.setStyle("-fx-background-color: #3e7b91");
        }

        tag.getChildren().add(label);
        remark.setText("Remark: " + student.getRemark().value);
        pendingQuestion.setText("Pending Question: " + student.getPendingQuestion().value);

        if (student.getRemark().value == "") {
            remarkPane.setOpacity(0.3);
        }
        if (student.getPendingQuestion().value == "") {
            pqPane.setOpacity(0.3);
        }
    }
}


