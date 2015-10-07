package IPSEN2.generators.csv;

import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jamie on 5-10-2015.
 */
public class ImportCSV {

    //Methods
    public void importTable(String table) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a CSV file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV Files", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        List<String[]> content = this.readCSV(selectedFile);

        for(String[] r : content) {
            for(int i = 0; i < r.length; i++) {
                System.out.println(r[i]);
            }
        }
    }

    private List<String[]> readCSV(File file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ',', '"', 1);

        List<String[]> allRows = reader.readAll();

        return allRows;
    }

    private void importToDatabase() {

    }
}
