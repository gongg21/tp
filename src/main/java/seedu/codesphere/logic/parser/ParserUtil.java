package seedu.codesphere.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.commons.util.StringUtil;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.model.course.CourseName;
import seedu.codesphere.model.person.Email;
import seedu.codesphere.model.person.Name;
import seedu.codesphere.model.person.Remark;
import seedu.codesphere.model.tag.StudentRank;
import seedu.codesphere.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String courseName} into a {@code courseName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseName} is invalid.
     */
    public static CourseName parseCourseName(String courseName) throws ParseException {
        requireNonNull(courseName);
        String trimmedCourseName = courseName.trim();
        if (!CourseName.isValidCourseName(trimmedCourseName)) {
            throw new ParseException(CourseName.MESSAGE_CONSTRAINTS);
        }
        return new CourseName(trimmedCourseName);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code StudentRank tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (trimmedTag.toUpperCase().equals(StudentRank.GOOD.toString())) {
            return new Tag(StudentRank.GOOD);
        } else if (trimmedTag.toUpperCase().equals(StudentRank.POOR.toString())) {
            return new Tag(StudentRank.POOR);
        } else if (trimmedTag.toUpperCase().equals(StudentRank.AVERAGE.toString())) {
            return new Tag(StudentRank.AVERAGE);
        } else {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS_ENUMS);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
