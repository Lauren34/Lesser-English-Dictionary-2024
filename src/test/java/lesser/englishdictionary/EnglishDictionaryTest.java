package lesser.englishdictionary;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnglishDictionaryTest {
    @Test
    public void testGetDefinition() throws CsvValidationException, IOException {
        // Given
        EnglishDictionary dictionary = new EnglishDictionary();

        // When
        List<String> actualDefinitions = dictionary.getDefinition("Aberration");

        // Then
        List<String> expectedDefinitions = Arrays.asList(
                "A deviation from the normal or typical.",
                "Anomaly; irregularity."
        );

        assertEquals(expectedDefinitions, actualDefinitions);
    }
}
