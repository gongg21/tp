package seedu.codesphere.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;
import seedu.codesphere.model.person.Remark;
import seedu.codesphere.model.person.Student;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;
import seedu.codesphere.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REMARK = "Likes skiing.";

    private Name name;
    private Email email;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark("");
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public StudentBuilder withTags(StudentRank ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public StudentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Student build() {
        return new Student(name, email, remark, tags);
    }
}
