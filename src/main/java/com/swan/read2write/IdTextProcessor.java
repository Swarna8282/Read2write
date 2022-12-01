package com.swan.read2write;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class IdTextProcessor implements ItemProcessor<String, IdText> {
    @Autowired
    public Util utility;

    @Override
    public IdText process(String s) throws Exception {
        return new IdText(Integer.parseInt(s), utility.convertStringIdToText(s));
    }
}
