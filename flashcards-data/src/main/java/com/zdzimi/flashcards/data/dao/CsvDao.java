package com.zdzimi.flashcards.data.dao;

import com.zdzimi.flashcards.core.model.Flashcard;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class CsvDao {

    public List<Flashcard> loadFile(File file, int categoryId) throws IOException {
        List<Flashcard> flashcards = new ArrayList<>();

        Reader reader = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
        for (CSVRecord record:records) {
            String question = record.get(0);
            String answer = "";
            for (int i = 1; i < record.size(); i++) {
                answer = i == 1 ? record.get(i) : answer + "," + record.get(i);
            }
            flashcards.add(new Flashcard(question, answer, categoryId));
        }
        return flashcards;
    }
}