package lesser.englishdictionary;

import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EnglishDictionaryGUI extends JFrame {

    private final EnglishDictionary englishDictionary;
    private final JTextArea definitionsArea;

    public EnglishDictionaryGUI() {
        setTitle("English Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextField wordField = new JTextField();
        wordField.setFont(new Font("Arial", Font.PLAIN, 24));
        wordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDefinitions(wordField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDefinitions(wordField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDefinitions(wordField.getText());
            }
        });
        add(wordField, BorderLayout.NORTH);

        definitionsArea = new JTextArea();
        definitionsArea.setFont(new Font("Arial", Font.PLAIN, 18));
        definitionsArea.setEditable(false);
        add(new JScrollPane(definitionsArea), BorderLayout.CENTER);

        try {
            englishDictionary = new EnglishDictionary();
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize English Dictionary");
        }

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDefinitions(String word) {
        List<String> definitions = englishDictionary.getDefinition(word);
        definitionsArea.setText("");
        for (String definition : definitions) {
            definitionsArea.append(definition + "\n");
        }
    }
}
