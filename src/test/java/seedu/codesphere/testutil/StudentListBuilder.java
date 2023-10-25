package seedu.codesphere.testutil;

import seedu.codesphere.model.StudentList;
import seedu.codesphere.model.person.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class StudentListBuilder {

    private StudentList studentList;

    public StudentListBuilder() {
        studentList = new StudentList();
    }

    public StudentListBuilder(StudentList studentList) {
        this.studentList = studentList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public StudentListBuilder withPerson(Student student) {
        studentList.addStudent(student);
        return this;
    }

    public StudentList build() {
        return studentList;
    }
}
