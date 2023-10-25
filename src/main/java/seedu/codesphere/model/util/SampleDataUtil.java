package seedu.codesphere.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.codesphere.model.CourseList;
import seedu.codesphere.model.ReadOnlyCourseList;
import seedu.codesphere.model.ReadOnlyStudentList;
import seedu.codesphere.model.StudentList;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;
import seedu.codesphere.model.person.Remark;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@u.nus.edu"), EMPTY_REMARK,
                    getTagSet(StudentRank.GOOD)),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.POOR)),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.POOR)),
            new Student(new Name("David Li"), new Email("lidavid@u.nus.edu"), EMPTY_REMARK,
                    getTagSet(StudentRank.AVERAGE)),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.GOOD)),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@u.nus.edu"),
                    EMPTY_REMARK, getTagSet(StudentRank.GOOD))
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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(StudentRank... ranking) {
        return Arrays.stream(ranking)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
