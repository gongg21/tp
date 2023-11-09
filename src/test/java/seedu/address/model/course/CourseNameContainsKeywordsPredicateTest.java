package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.TypicalCourses;

public class CourseNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CourseNameContainsKeywordsPredicate firstPredicate =
                new CourseNameContainsKeywordsPredicate(firstPredicateKeywordList);
        CourseNameContainsKeywordsPredicate secondPredicate =
                new CourseNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CourseNameContainsKeywordsPredicate firstPredicateCopy =
                new CourseNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_courseNameContainsKeywords_returnsTrue() {
        // One keyword
        CourseNameContainsKeywordsPredicate predicate =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("CS1101S"));
        assertTrue(predicate.test(TypicalCourses.CS1101S));

        // Multiple keywords
        predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("CS", "ST"));
        assertTrue(predicate.test(new CourseBuilder().withCourseName("CS2030S").build()));

        // Mixed-case keywords
        predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("cS", "St"));
        assertTrue(predicate.test(new CourseBuilder().withCourseName("ST2334").build()));

        // Only one matching keyword does find
        predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("C"));
        assertTrue(predicate.test(TypicalCourses.CS2030S));
    }

    @Test
    public void test_courseNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new CourseBuilder().withCourseName("CS1101S").build()));

        // Non-matching keyword
        predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("ST"));
        assertFalse(predicate.test(new CourseBuilder().withCourseName("CS2103T").build()));
    }
}
