package misc;

public class Palindrome {
    public static boolean canFormPalindromeByRemovingAtMostOneChar(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        str = str.toLowerCase();

        int low = 0;
        int high = str.length() - 1;
        while (low < high) {
            if (str.charAt(low) != str.charAt(high)) {
                return isPalindrome(str, low + 1, high) ||
                        isPalindrome(str, low, high - 1);
            }
            else {
                ++low;
                --high;
            }
        }

        return true;
    }

    public static boolean isPalindrome(String str, int low, int high) {
        while (low < high) {
            if (str.charAt(low) != str.charAt(high)) {
                return false;
            }
            ++low;
            --high;
        }
        return true;
    }
}
