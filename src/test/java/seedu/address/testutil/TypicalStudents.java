package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PENDING_QUESTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AVERAGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.StudentList;
import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("e0000000@u.nus.edu").withRemark("She likes aardvarks.")
            .withTag(VALID_TAG_AVERAGE).build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("e1111111@u.nus.edu").withRemark("He can't take beer!")
            .withTag(VALID_TAG_GOOD).build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withEmail("e2222222@u.nus.edu").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withEmail("e3333333@u.nus.edu").withTag(VALID_TAG_GOOD).build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withEmail("e4444444@u.nus.edu").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withEmail("e5555555@u.nus.edu").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withEmail("e6666666@u.nus.edu").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withEmail("e7777777@u.nus.edu").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withEmail("e8888888@u.nus.edu").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_EMAIL_AMY).withTag(VALID_TAG_AVERAGE).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_EMAIL_BOB).withTag(VALID_TAG_POOR).withPendingQuestion(VALID_PENDING_QUESTION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private final ObservableList<Student> internalList = FXCollections.observableArrayList();


    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Students.
     */
    public static StudentList getTypicalStudentList() {
        StudentList sl = new StudentList();
        for (Student student : getTypicalStudents()) {
            sl.addStudent(student);
        }
        return sl;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

}
