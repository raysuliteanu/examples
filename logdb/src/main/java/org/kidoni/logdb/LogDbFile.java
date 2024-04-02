package org.kidoni.logdb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import static java.io.File.createTempFile;
import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.file.StandardOpenOption.*;

public class LogDbFile {
    public static final int DEFAULT_FILE_SIZE = 1_024 * 1_024 * 10;

    private final Path filePath;
    private MappedByteBuffer mappedByteBuffer;
    private boolean isReadOnly;

    // TODO: support configuration property to override default file size
    private long fileSize = DEFAULT_FILE_SIZE;

    public static synchronized LogDbFile newFile() throws IOException {
        Path path = createTempFile("data", "." + FileHeader.TYPE).toPath();
        return new LogDbFile(path, true, APPEND);
    }

    public static synchronized LogDbFile openFileForRead(Path filePath) throws IOException {
        return new LogDbFile(filePath, false, READ);
    }

    public static synchronized LogDbFile openFileForWrite(Path filePath) throws IOException {
        return new LogDbFile(filePath, false, APPEND);
    }

    LogDbFile(final Path filePath, boolean isNew, final OpenOption openOption) throws IOException {
        if (isNew) {
            createFile(filePath);
        }
        else {
            mountFile(filePath, openOption);
            if (openOption == READ) {
                isReadOnly = true;
            }
        }

        this.filePath = filePath;
    }

    private void createFile(Path filePath) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filePath, CREATE, READ, WRITE)) {
            mapFile(fileChannel, FileChannel.MapMode.READ_WRITE);
        }
        writeFileHeader();
    }

    private void mountFile(final Path filePath, final OpenOption openOption) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(filePath, openOption)) {
            mapFile(fileChannel, openOption == READ ? FileChannel.MapMode.READ_ONLY : FileChannel.MapMode.READ_WRITE);
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

    private void mapFile(final FileChannel fileChannel, FileChannel.MapMode mode) throws IOException {
        mappedByteBuffer = fileChannel.map(mode, 0L, fileSize);
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

    public OutputStream getOutputStream() {
        if (isReadOnly) {
            throw new IllegalArgumentException("file open for reading only");
        }

        return new OutputStream() {
            @Override
            public void write(final byte[] b) throws IOException {
                mappedByteBuffer.put(b);
            }

            @Override
            public void write(final int b) throws IOException {
                write(new byte[]{(byte) b});
            }
        };
    }

    public InputStream getInputStream() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }

            @Override
            public int read(final byte[] b) throws IOException {
                int pos = mappedByteBuffer.position();
                mappedByteBuffer.get(b);
                return mappedByteBuffer.position() - pos;
            }
        };
    }

    Path filePath() {
        return filePath;
    }

    long fileSize() {
        return fileSize;
    }
}
