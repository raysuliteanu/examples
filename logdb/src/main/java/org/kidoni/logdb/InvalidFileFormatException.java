package org.kidoni.logdb;

import java.io.IOException;

public class InvalidFileFormatException extends IOException {
    public InvalidFileFormatException() {
        super();
    }

    public InvalidFileFormatException(final String message) {
        super(message);
    }

    public InvalidFileFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidFileFormatException(final Throwable cause) {
        super(cause);
    }
}
