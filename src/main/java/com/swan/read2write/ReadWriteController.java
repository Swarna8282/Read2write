package com.swan.read2write;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.swan.read2write.Constants.TEST_DATA_FILE_NAME;

@RestController
@RequestMapping(value = "/readWrite")
@Slf4j
public class ReadWriteController {
    private final ReadWriteService rwService;
    private final Util utility;

    public ReadWriteController(ReadWriteService rwService, Util utility) {
        this.rwService = rwService;
        this.utility = utility;
    }

    @GetMapping(value = "/idText")
    private List<IdText> getAllTexts() {
        return rwService.getAllTexts();
    }

    @GetMapping(value = "/idText/{id}")
    private IdText getText(@PathVariable("id") int id) {
        return rwService.getText(id);
    }

    @DeleteMapping("/idText/{id}")
    private void deleteText(@PathVariable("id") int id) {
        rwService.delete(id);
    }

    @PostMapping("/idText")
    private int saveText(@RequestBody IdText idText) {
        rwService.saveOrUpdate(idText);
        return idText.getId();
    }

    @PostMapping("/idText/{id}")
    private int saveText(@PathVariable("id") int id) {
        IdText text = new IdText(id, utility.convertIdToText(id));
        rwService.saveOrUpdate(text);
        return text.getId();
    }

    @PostMapping("/file")
    private String readFromFileAndSaveToDb() {
        return readFromFileAndSaveToDb(TEST_DATA_FILE_NAME);
    }

    @PostMapping("/file/{fileName}")
    private String readFromFileAndSaveToDb(@PathVariable("fileName") String fileName) {
        if (fileName.isBlank()) {
            log.info("File Name is Blank!");
            fileName = TEST_DATA_FILE_NAME;
        }
        try {
            log.info("File Name = " + fileName);
            return rwService.readFileAndWrite2Db(fileName);
        } catch (IOException e) {
            log.error("Failed to Read the File and save to H2 DB.", e);
            return "Failed to read a Line in the file provided.";
        }
    }
}
