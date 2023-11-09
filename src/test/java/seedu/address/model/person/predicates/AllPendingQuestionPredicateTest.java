package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalStudents;

public class AllPendingQuestionPredicateTest {

    @Test
    public void equals() {

        AllPendingQuestionPredicate firstPredicate = new AllPendingQuestionPredicate();
        AllPendingQuestionPredicate secondPredicate = new AllPendingQuestionPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        AllPendingQuestionPredicate predicate = new AllPendingQuestionPredicate();
        assertTrue(predicate.test(TypicalStudents.ALICE));
    }

    @Test
    public void toStringMethod() {
        AllPendingQuestionPredicate predicate = new AllPendingQuestionPredicate();

        String expected = AllPendingQuestionPredicate.class.getCanonicalName() + "{}";
        assertEquals(expected, predicate.toString());
    }
}
