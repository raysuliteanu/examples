package encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationCipherTest {
    @Test
    void rotate() {
        String result = RotationCipher.rotationalCipher("abc123-!", 4);
        assertEquals("efg567-!", result);

        result = RotationCipher.rotationalCipher("abc123-!", 26);
        assertEquals("abc789-!", result);
    }
}