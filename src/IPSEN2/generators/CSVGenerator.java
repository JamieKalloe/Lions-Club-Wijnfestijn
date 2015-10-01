package IPSEN2.generators;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Jamie on 1-10-2015.
 */
public class CSVGenerator {

    public void importGuests() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select excel sheet");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel files", "*.xlsx"),
                new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        //toCSV...
        //Read values...
        //Use GuestService to add the imported data to the database...
    }

    private void toCSV(File document) throws IOException {
        FileInputStream input_document = new FileInputStream(document);
        //TODO: maak gebruik van de APACHE POI api...
    }
}
