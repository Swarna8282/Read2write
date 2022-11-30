package com.swan.read2write;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.swan.read2write.Constants.RECORDS_COUNT;
import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;

@SpringBootTest
public class CreateTestFileIT {

    @Test
    public void createFile() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(TEST_DATA_FILE_NAME);
        for (int i = 1; i <= RECORDS_COUNT; i++) {
            outputStream.write(("" + i + "\n").getBytes());
        }
        outputStream.close();
    }
}
