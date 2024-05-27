package lesser.englishdictionary;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Reads the englishDictionary file ONCE.
 */
public class EnglishDictionary {
    private final Map<String, List<String>> words = new HashMap<>();

    public EnglishDictionary() throws CsvValidationException, IOException {
        // gets the file from the "resources" directory
        InputStream in = EnglishDictionary.class.getResourceAsStream("/englishDictionary.csv");
        CSVReader reader = new CSVReader(new InputStreamReader(in));
        String[] record;

        while ((record = reader.readNext()) != null) {
            // record is ONE line of the CSV
            List<String> list = words.getOrDefault(record[0], new ArrayList<>());
            list.add(record[2]);
            words.put(record[0], list);
        }
        reader.close();
    }

    /**
     * @param word to look up.
     * @return a List of definitions, or an empty list if the word doesn't exist.
     */
    public List<String> getDefinition(String word) {
        return words.getOrDefault(word, Collections.emptyList());
    }

    public static void main(String[] args) {
         new EnglishDictionaryGUI();
    }
}
