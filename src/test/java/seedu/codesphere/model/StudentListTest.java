package seedu.codesphere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codesphere.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.codesphere.testutil.Assert.assertThrows;
import static seedu.codesphere.testutil.TypicalStudents.ALICE;
import static seedu.codesphere.testutil.TypicalStudents.getTypicalStudentList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.person.exceptions.DuplicateStudentException;
import seedu.codesphere.testutil.StudentBuilder;

public class StudentListTest {

    private final StudentList studentList = new StudentList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), studentList.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyStudentList_replacesData() {
        StudentList newData = getTypicalStudentList();
        studentList.resetData(newData);
        assertEquals(newData, studentList);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two Students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_GOOD)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        StudentListStub newData = new StudentListStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> studentList.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> studentList.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentList_returnsFalse() {
        assertFalse(studentList.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudentList_returnsTrue() {
        studentList.addStudent(ALICE);
        assertTrue(studentList.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInStudentList_returnsTrue() {
        studentList.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_GOOD)
                .build();
        assertTrue(studentList.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> studentList.getStudentList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = StudentList.class.getCanonicalName() + "{Students=" + studentList.getStudentList() + "}";
        assertEquals(expected, studentList.toString());
    }

    /**
     * A stub ReadOnlyStudentList whose Students list can violate interface constraints.
     */
    private static class StudentListStub implements ReadOnlyStudentList {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        StudentListStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
