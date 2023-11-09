package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PendingQuestion;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.StudentRank;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_COURSE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "im a invalid tag";
    private static final String INVALID_REMARK = "";
    private static final String INVALID_PENDING_QUESTION = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_COURSE = "CS2103T";
    private static final String VALID_EMAIL = "e1234567@u.nus.edu";
    private static final StudentRank VALID_TAG_GOOD = StudentRank.GOOD;
    private static final StudentRank VALID_TAG_AVERAGE = StudentRank.AVERAGE;
    private static final StudentRank VALID_TAG_POOR = StudentRank.POOR;
    private static final String VALID_REMARK = "im a valid remark";
    private static final String VALID_PENDING_QUESTION = "im a valid pending question";


    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseCourse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourseName((String) null));
    }

    @Test
    public void parseCourse_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCourseName(INVALID_COURSE));
    }

    @Test
    public void parseCourse_validValueWithoutWhitespace_returnsCourse() throws Exception {
        CourseName courseName = ParserUtil.parseCourseName(VALID_COURSE);
        CourseName expectedCourseName = new CourseName(VALID_COURSE);
        assertEquals(expectedCourseName, courseName);
    }

    @Test
    public void parseCourse_validValueWithWhitespace_returnsTrimmedCourse() throws Exception {
        String courseWithWhitespace = WHITESPACE + VALID_COURSE + WHITESPACE;
        CourseName expectedCourseName = new CourseName(VALID_COURSE);
        assertEquals(expectedCourseName, ParserUtil.parseCourseName(courseWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        // good tag
        Tag expectedGoodTag = new Tag(VALID_TAG_GOOD);
        assertEquals(expectedGoodTag, ParserUtil.parseTag(VALID_TAG_GOOD.toString()));

        // average tag
        Tag expectedAverageTag = new Tag(VALID_TAG_AVERAGE);
        assertEquals(expectedAverageTag, ParserUtil.parseTag(VALID_TAG_AVERAGE.toString()));

        // poor tag
        Tag expectedPoorTag = new Tag(VALID_TAG_POOR);
        assertEquals(expectedPoorTag, ParserUtil.parseTag(VALID_TAG_POOR.toString()));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        // good tag
        String goodTagWithWhitespace = WHITESPACE + VALID_TAG_GOOD + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_GOOD);
        assertEquals(expectedTag, ParserUtil.parseTag(goodTagWithWhitespace));

        // average tag
        String averageTagWithWhitespace = WHITESPACE + VALID_TAG_AVERAGE + WHITESPACE;
        Tag expectedAverageTag = new Tag(VALID_TAG_AVERAGE);
        assertEquals(expectedAverageTag, ParserUtil.parseTag(averageTagWithWhitespace));

        // poor tag
        String poorTagWithWhitespace = WHITESPACE + VALID_TAG_POOR + WHITESPACE;
        Tag expectedPoorTag = new Tag(VALID_TAG_POOR);
        assertEquals(expectedPoorTag, ParserUtil.parseTag(poorTagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG.toString()));
    }

    @Test
    public void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRemark((String) null));
    }

    @Test
    public void parseRemark_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRemark(INVALID_REMARK));
    }

    @Test
    public void parseRemark_validValueWithoutWhitespace_returnsRemark() throws Exception {
        Remark expectedRemark = new Remark(VALID_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(VALID_REMARK));
    }

    @Test
    public void parseRemark_validValueWithWhitespace_returnsTrimmedRemark() throws Exception {
        String remarkWithWhitespace = WHITESPACE + VALID_REMARK + WHITESPACE;
        Remark expectedRemark = new Remark(VALID_REMARK);
        assertEquals(expectedRemark, ParserUtil.parseRemark(remarkWithWhitespace));
    }

    @Test
    public void parsePendingQuestion_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePendingQuestion((String) null));
    }

    @Test
    public void parsePendingQuestion_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePendingQuestion(INVALID_PENDING_QUESTION));
    }

    @Test
    public void parsePendingQuestion_validValueWithoutWhitespace_returnsPendingQuestion() throws Exception {
        PendingQuestion expectedPendingQuestion = new PendingQuestion(VALID_PENDING_QUESTION);
        assertEquals(expectedPendingQuestion, ParserUtil.parsePendingQuestion(VALID_PENDING_QUESTION));
    }

    @Test
    public void parsePendingQuestion_validValueWithWhitespace_returnsTrimmedPendingQuestion() throws Exception {
        String pendingQuestionWithWhitespace = WHITESPACE + VALID_PENDING_QUESTION + WHITESPACE;
        PendingQuestion expectedPendingQuestion = new PendingQuestion(VALID_PENDING_QUESTION);
        assertEquals(expectedPendingQuestion, ParserUtil.parsePendingQuestion(pendingQuestionWithWhitespace));
    }
}
