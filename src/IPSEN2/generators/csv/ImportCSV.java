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

    //Variables
    private final String fileChooserTitle = "Import csv file";

    //Methods
    public void importTable(String table) throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.fileChooserTitle);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("CSV Files", "*.csv")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        List<String[]> content = this.readCSV(selectedFile);

        ArrayList<String[]> rows = this.formatData(content, ";");

        //test the data (separated).
        for(String[] customerData : rows) {
            for(int i = 0; i < customerData.length; i++) {
                System.out.println(customerData[i]);
            }
            System.out.println("_______________________");
        }
    }

    private List<String[]> readCSV(File file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ',', '"', 1);

        List<String[]> allRows = reader.readAll();

        return allRows;
    }

    private ArrayList<String[]> formatData(List<String[]> data, String formatKey) {
        ArrayList<String[]> formattedData = new ArrayList();
        for(String[] array : data) {
            for(int i = 0; i < array.length; i++) {
                String[] row = array[i].split(";");
                formattedData.add(row);
            }
        }

        return formattedData;
    }
}
