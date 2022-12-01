package com.swan.read2write;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.swan.read2write.Constants.RECORDS_COUNT;
import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReadWriteIT {
    @Autowired
    Util utility;
    @Autowired
    ReadWriteRepo rwRepo;
    @Autowired
    ReadWriteService rwService;

    @BeforeEach
    void init() {
        try {
            utility.createLocalFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRecordsInH2Db() {
        try {
            rwService.readFileAndWrite2Db(TEST_DATA_FILE_NAME);
            assertEquals(RECORDS_COUNT, rwService.getAllTexts().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
