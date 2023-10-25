package seedu.address.model.util;

import seedu.address.model.CourseList;
import seedu.address.model.ReadOnlyCourseList;
import seedu.address.model.ReadOnlyStudentList;
import seedu.address.model.StudentList;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Student;
import seedu.address.model.tag.StudentRank;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static final PendingQuestion EMPTY_PENDING_QUESTION = new PendingQuestion("");
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@u.nus.edu"), EMPTY_REMARK, EMPTY_PENDING_QUESTION,
                    new Tag(StudentRank.GOOD)),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.POOR)),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.POOR)),
            new Student(new Name("David Li"), new Email("lidavid@u.nus.edu"), EMPTY_REMARK, EMPTY_PENDING_QUESTION,
                    new Tag(StudentRank.AVERAGE)),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.GOOD)),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"),
                    EMPTY_REMARK, EMPTY_PENDING_QUESTION, new Tag(StudentRank.GOOD))
        };
    }

    public static Course[] getSampleCourses() {
        return new Course[] {
            new Course(new CourseName("CS1101S")),
            new Course(new CourseName("CS1231S")),
            new Course(new CourseName("CS2030S")),
            new Course(new CourseName("CS2040S")),
            new Course(new CourseName("CS2100")),
            new Course(new CourseName("CS2103T")),
        };
    }

    public static ReadOnlyStudentList getSampleStudentList() {
        StudentList sampleSl = new StudentList();
        for (Student sampleStudent : getSampleStudents()) {
            sampleSl.addStudent(sampleStudent);
        }
        return sampleSl;
    }

    public static ReadOnlyCourseList getSampleCourseList() {
        CourseList sampleCl = new CourseList();
        for (Course sampleCourse : getSampleCourses()) {
            sampleCl.addCourse(sampleCourse);
        }
        return sampleCl;
    }
}
