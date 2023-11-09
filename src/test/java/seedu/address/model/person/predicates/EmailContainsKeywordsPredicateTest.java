package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.StudentRank;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalStudents;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("e0000000"));
        assertTrue(predicate.test(TypicalStudents.ALICE));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("E1234567"));
        assertTrue(predicate.test(new StudentBuilder().withName(StudentBuilder.DEFAULT_NAME)
                .withEmail("e1234567@u.nus.edu").withTag(StudentBuilder.DEFAULT_STUDENT_RANK).build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        EmailContainsKeywordsPredicate predicate;

        // Only one matching keyword does not find
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("e", "19"));
        assertFalse(predicate.test(TypicalStudents.CARL));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("e1234516"));
        assertFalse(predicate.test(new StudentBuilder().withEmail("e1234517@u.nus.edu").build()));

        // Keywords match tag, name, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("GOOD", StudentBuilder.DEFAULT_NAME));
        assertFalse(predicate.test(new StudentBuilder().withName(StudentBuilder.DEFAULT_NAME)
                .withEmail("e0726516@u.nus.edu")
                .withTag(StudentRank.GOOD).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(keywords);

        String expected = EmailContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
