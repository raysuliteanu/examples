package misc;

import org.junit.jupiter.api.Test;

import static misc.Palindrome.canFormPalindromeByRemovingAtMostOneChar;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeTest {
    @Test
    void shouldBeTrue() {
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("abcba"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("abccba"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("abbcba"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("aabcba"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("abcbaa"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("aBcbAa"));
        assertTrue(canFormPalindromeByRemovingAtMostOneChar("ab"));
    }

    @Test
    void shouldBeFalse() {
        assertFalse(canFormPalindromeByRemovingAtMostOneChar(""));
        assertFalse(canFormPalindromeByRemovingAtMostOneChar("abc"));
        assertFalse(canFormPalindromeByRemovingAtMostOneChar("abcabc"));
    }
}