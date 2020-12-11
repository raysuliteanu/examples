package org.kidoni.logdb;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static java.io.File.createTempFile;
import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.DELETE_ON_CLOSE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectByteBufferTests {

    @Test
    void fileHeader() {
        FileHeader header = FileHeader.createHeader();
        assertEquals(FileHeader.TYPE, header.type());
        assertEquals(FileHeader.VERSION, header.version());
    }

    @Test
    void directMappedByteBuffer() throws IOException {
        Path path = createTempFile("data", FileHeader.TYPE).toPath();
        FileChannel fileChannel = FileChannel.open(path, CREATE, DELETE_ON_CLOSE, READ, WRITE);
        MappedByteBuffer buffer = fileChannel.map(READ_WRITE, 0, 256);
        FileHeader header = FileHeader.createHeader();
        byte[] bytes = header.type().getBytes(US_ASCII);
        assertEquals(FileHeader.TYPE.length(), bytes.length);
        buffer.put(bytes);
        bytes = header.version().getBytes(US_ASCII);
        assertEquals(FileHeader.VERSION.length(), bytes.length);
        buffer.put(bytes);

        buffer.rewind();

        int headerLength = header.type().length() + header.version().length();
        byte[] headerBytes = new byte[headerLength];
        buffer.get(headerBytes);
        assertEquals(headerLength, buffer.position());
        String type = new String(headerBytes, 0, header.type().length(), US_ASCII);
        assertEquals(FileHeader.TYPE, type);
        String version = new String(headerBytes, header.type().length(), header.version().length(), US_ASCII);
        assertEquals(FileHeader.VERSION, version);
    }
}