package com.swan.read2write;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

import static com.swan.read2write.Constants.RECORDS_COUNT;
import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;

@Component
@Slf4j
public class Util {

    public void createLocalFile() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(TEST_DATA_FILE_NAME);
        for (int i = 1; i <= RECORDS_COUNT; i++) {
            outputStream.write(("" + i + "\n").getBytes());
        }
        outputStream.close();
    }

    public String getCertainLineFromFile (String fileName, int position) throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        for (int i=0; i<position-1; i++)
            bReader.readLine();
        String lineString = bReader.readLine();
        bReader.close();
        return lineString;
    }

    public String convertIdToText(int uniqueId) {
        String temp = ""+uniqueId;
        return convertStringIdToText(temp);
    }

    public String convertStringIdToText(String uniqueId) {
        String idWord = "";
        for (int i=0; i<uniqueId.length(); i++) {
            switch (uniqueId.charAt(i)) {
                case '0' -> idWord += "Zero ";
                case '1' -> idWord += "One ";
                case '2' -> idWord += "Two ";
                case '3' -> idWord += "Three ";
                case '4' -> idWord += "Four ";
                case '5' -> idWord += "Five ";
                case '6' -> idWord += "Six ";
                case '7' -> idWord += "Seven ";
                case '8' -> idWord += "Eight ";
                case '9' -> idWord += "Nine ";
            }
        }
        return idWord;
    }
}
