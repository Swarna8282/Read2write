package com.swan.read2write;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.swan.read2write.Constants.BATCH_SIZE;

@Service
@Slf4j
public class ReadWriteService {
    private final ReadWriteRepo rwRepo;
    private final Util utility;

    public ReadWriteService(ReadWriteRepo rwRepo, Util utility) {
        this.rwRepo = rwRepo;
        this.utility = utility;
    }

    public String readFileAndWrite2Db(String fileName) throws IOException {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            log.error("File " + fileName + " is not found.", e);
            return "File " + fileName + " is not found.";
        }
        List<IdText> texts = new ArrayList<IdText>();
        String tempIdStr = "";
        int tempId = -1;
        int iteratorCount = 0;
        while ((tempIdStr = bReader.readLine()) != null) {
            try {
                if (!tempIdStr.isBlank() && (tempId = Integer.parseInt(tempIdStr))>-1) {
                    texts.add(new IdText(tempId, utility.convertStringIdToText(tempIdStr)));
                }
                if (texts.size() > BATCH_SIZE) {
                    rwRepo.saveAll(texts);
                    texts.clear();
                    iteratorCount++;
                    log.info("Wrote " + BATCH_SIZE + " records into the database in Iteration " + iteratorCount + ".");
                }
            } catch (NumberFormatException e) {
                log.error("Provided String " + tempIdStr + " is not a number.", e);
            }
        }
        if (!texts.isEmpty())
            rwRepo.saveAll(texts);
        return "Successfully wrote the data into H2 DB.";
    }

    public List<IdText> getAllTexts() {
        List<IdText> texts = new ArrayList<IdText>();
        rwRepo.findAll().forEach(texts::add);
        return texts;
    }

    public long getRowsCount() {
        return rwRepo.count();
    }

    public IdText getText(int id) {
        return rwRepo.findById(id).get();
    }

    public void delete(int id) {
        rwRepo.deleteById(id);
    }

    public void saveOrUpdate(IdText idText) {
        rwRepo.save(idText);
    }
}
