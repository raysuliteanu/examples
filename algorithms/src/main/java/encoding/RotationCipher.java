package encoding;

public class RotationCipher {

    public static String rotationalCipher(String input, int rotationFactor) {
        StringBuilder builder = new StringBuilder();
        for (char aChar : input.toCharArray()) {
            if (aChar >= 'A' && aChar <= 'Z') {
                char rotated = rotate(rotationFactor, aChar, 'A', 26);
                builder.append(rotated);
            } else if (aChar >= 'a' && aChar <= 'z') {
                char rotated = rotate(rotationFactor, aChar, 'a', 26);
                builder.append(rotated);
            } else if (aChar >= '0' && aChar <= '9') {
                char rotated = rotate(rotationFactor, aChar, '0', 10);
                builder.append(rotated);
            } else {
                // don't rotate punctuation
                builder.append(aChar);
            }
        }
        return builder.toString();
    }

    private static char rotate(final int rotationFactor, final char charToRotate, final char base, final int alphabetSize) {
        return (char) ((((charToRotate - base) + rotationFactor) % alphabetSize) + base);
    }
}
