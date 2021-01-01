package org.kidoni.logdb;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.io.File.createTempFile;
import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class LogDbFile {
    public static final int DEFAULT_FILE_SIZE = 1_024 * 1_024 * 10;

    private final Path filePath;
    private MappedByteBuffer mappedByteBuffer;

    // TODO: support configuration property to override default file size
    private long fileSize = DEFAULT_FILE_SIZE;

    public static synchronized LogDbFile newFile() throws IOException {
        Path path = createTempFile("data", "." + FileHeader.TYPE).toPath();
        return new LogDbFile(path, true);
    }

    public static synchronized LogDbFile openFile(Path filePath) throws IOException {
        return new LogDbFile(filePath, false);
    }

    LogDbFile(final Path filePath, boolean isNew) throws IOException {
        if (isNew) {
            createFile(filePath);
        }
        else {
            mountFile(filePath);
        }

        this.filePath = filePath;
    }

    private void mountFile(final Path filePath) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filePath, READ, WRITE)) {
            mapFile(fileChannel);
        }

        verifyFileHeader();
    }

    private void verifyFileHeader() throws InvalidFileFormatException {
        FileHeader header = FileHeader.createHeader();
        int headerLength = header.headerSize();
        byte[] headerBytes = new byte[headerLength];
        mappedByteBuffer.get(headerBytes);

        if (headerLength != mappedByteBuffer.position()) {
            throw new InvalidFileFormatException("invalid file header length for file " + filePath);
        }

        String type = new String(headerBytes, 0, header.type().length(), US_ASCII);
        String version = new String(headerBytes, header.type().length(), header.version().length(), US_ASCII);

        if (!(header.type().equals(type) && header.version().equals(version))) {
            throw new InvalidFileFormatException("invalid file header type or version for file " + filePath);
        }
    }

    private void createFile(Path filePath) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filePath, CREATE, READ, WRITE)) {
            mapFile(fileChannel);
        }
        writeFileHeader();
    }

    private void mapFile(final FileChannel fileChannel) throws IOException {
        mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0L, fileSize);
    }

    private void writeFileHeader() {
        FileHeader header = FileHeader.createHeader();
        int headerLength = header.headerSize();
        byte[] headerBytes = new byte[headerLength];
        arraycopy(header.type().getBytes(US_ASCII), 0, headerBytes, 0, header.type().length());
        arraycopy(header.version().getBytes(US_ASCII), 0, headerBytes, header.type().length(), header.version().length());
        mappedByteBuffer.put(headerBytes);
    }

    public long capacity() {
        return fileSize - mappedByteBuffer.position();
    }

    Path filePath() {
        return filePath;
    }

    long fileSize() {
        return fileSize;
    }
}
