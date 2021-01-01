package org.kidoni.logdb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kidoni.logdb.FileHeader.TYPE;
import static org.kidoni.logdb.FileHeader.VERSION;
import static org.kidoni.logdb.FileHeader.createHeader;

public class DirectByteBufferTests {

    @Test
    void fileHeader() {
        FileHeader header = createHeader();
        assertEquals(TYPE, header.type());
        assertEquals(VERSION, header.version());
    }

    @Test
    void createLogDbFile() throws IOException {
        LogDbFile logDbFile = LogDbFile.newFile();
        long capacity = logDbFile.capacity();
        assertEquals(logDbFile.fileSize() - createHeader().headerSize(), capacity);

        deleteFile(logDbFile);
    }

    @Test
    public void openFile() throws IOException {
        LogDbFile newFile = LogDbFile.newFile();
        Path path = newFile.filePath();
        LogDbFile openFile = LogDbFile.openFile(path);
        assertEquals(newFile.capacity(), openFile.capacity());

        deleteFile(newFile);
        deleteFile(openFile);
    }

    private void deleteFile(LogDbFile logDbFile) {
        File file = logDbFile.filePath().toFile();
        if (file.exists()) {
            file.deleteOnExit();
        }
    }
}