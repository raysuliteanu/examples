package org.kidoni.logdb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.kidoni.logdb.model.PersonProto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kidoni.logdb.FileHeader.TYPE;
import static org.kidoni.logdb.FileHeader.VERSION;
import static org.kidoni.logdb.FileHeader.createHeader;

public class LogDbFileTest {

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
        LogDbFile openFile = LogDbFile.openFileForRead(path);
        assertEquals(newFile.capacity(), openFile.capacity());

        deleteFile(newFile);
    }

    private void deleteFile(LogDbFile logDbFile) {
        File file = logDbFile.filePath().toFile();
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    @Test
    void writeTest() throws IOException {
        PersonProto.Person.Builder builder = PersonProto.Person.newBuilder();
        PersonProto.Person person = builder.setFirstName("John")
                .setLastName("Doe")
                .setDateOfBirth("19700424")
                .addAddress(PersonProto.Person.Address.newBuilder()
                        .setStateOrProvince("666 Elm St")
                        .setCity("Hollywood")
                        .setStateOrProvince("CA")
                        .setCountry("USA")
                        .setPostCode("90211")
                        .build()
                )
                .build();

        LogDbFile logDbFile = LogDbFile.newFile();
        try (OutputStream outputStream = logDbFile.getOutputStream()) {
            person.writeTo(outputStream);
        }

        Path filePath = logDbFile.filePath();
        LogDbFile forReading = LogDbFile.openFileForRead(filePath);
        try (InputStream inputStream = forReading.getInputStream()) {
            int serializedSize = person.getSerializedSize();
            byte[] buffer = new byte[serializedSize];

            int read = inputStream.read(buffer);
            assertEquals(serializedSize, read);

            PersonProto.Person fromFile = PersonProto.Person.parseFrom(buffer);
            assertEquals(person, fromFile);
        }

        deleteFile(logDbFile);
    }
}