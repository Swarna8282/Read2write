package com.swan.read2write;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static com.swan.read2write.Constants.RECORDS_COUNT;
import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {
    @MockBean
    Util utilTest = new Util();

    @Test
    void convertIdToString_basic() {
        assertEquals("Three ", utilTest.convertIdToText(3));
    }

    @Test
    void getCertainLineFromFile_basic() throws IOException {
        assertEquals("3", utilTest.getCertainLineFromFile(TEST_DATA_FILE_NAME, 3));
    }

    @Test
    void getCertainLineFromFile_firstLine() throws IOException {
        assertEquals("1", utilTest.getCertainLineFromFile(TEST_DATA_FILE_NAME, 1));
    }

    @Test
    void getCertainLineFromFile_lastLine() throws IOException {
        assertEquals(""+RECORDS_COUNT,
                utilTest.getCertainLineFromFile(TEST_DATA_FILE_NAME, RECORDS_COUNT));
    }

    @Test
    void createLocalFile_basic() {
    }
}