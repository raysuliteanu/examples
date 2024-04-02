package misc;

public class StrongPassword {

    public static final int MIN_LEN = 6;
    public static final int MAX_LEN = 20;

    public static void main(String[] args) {
        final StrongPassword strongPassword = new StrongPassword();
        System.out.println(strongPassword.strongPasswordChecker("abcABC123"));
        System.out.println(strongPassword.strongPasswordChecker("Abc123"));
        System.out.println(strongPassword.strongPasswordChecker("aaB123"));

        System.out.println(strongPassword.strongPasswordChecker("abcABC123abcABC123abcABC123abcABC123abcABC123"));
        System.out.println(strongPassword.strongPasswordChecker("abcAB"));
        System.out.println(strongPassword.strongPasswordChecker("abcABC"));
        System.out.println(strongPassword.strongPasswordChecker("ABC123"));
        System.out.println(strongPassword.strongPasswordChecker("abc123"));
        System.out.println(strongPassword.strongPasswordChecker("aaa123"));
    }

    public int strongPasswordChecker(String password) {
        final boolean tooShort = password.length() < MIN_LEN;
        final boolean tooLong = password.length() > MAX_LEN;
        boolean invalidLength = tooShort || tooLong;
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        char lastChar = 0;
        int consecutiveCount = 0;
        boolean tooManyRepeatingChars = false;
        for (final char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }

            if (Character.isLowerCase(c)) {
                hasLower = true;
            }

            if (c == lastChar) {
                ++consecutiveCount;
                if (consecutiveCount == 3) {
                    tooManyRepeatingChars = true;
                }
            }
            else {
                consecutiveCount = 0;
            }
            lastChar = c;
        }

        int steps = 0;

        if (notStrong(invalidLength, hasDigit, hasUpper, hasLower, tooManyRepeatingChars)) {
            steps = 1;
        }

        return steps;
    }

    private boolean notStrong(final boolean invalidLength, final boolean hasDigit, final boolean hasUpper, final boolean hasLower, final boolean tooManyRepeatingChars) {
        return tooManyRepeatingChars || invalidLength || !(hasDigit && hasUpper && hasLower);
    }
}
