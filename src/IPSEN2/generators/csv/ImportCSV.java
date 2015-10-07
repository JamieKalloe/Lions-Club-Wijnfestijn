package IPSEN2.generators.csv;

import IPSEN2.services.guest.GuestService;
import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        List<String[]> content = readCSV(selectedFile);

        for(String[] customerData : content) {
            HashMap data = new HashMap();

            for(int record = 0; record < customerData.length; record++) {
                data.put("email", customerData[9]);
                data.put("firstname", customerData[2]);
                data.put("lastname", customerData[1]);
                data.put("prefix", customerData[3]);
                data.put("gender", customerData[4]);
                data.put("notes", "Not specified!");
                data.put("zipCode", customerData[7]);
                data.put("street", customerData[5]);
                data.put("houseNumber", customerData[6]);
                data.put("country", "Nederland");
                data.put("city", customerData[8]);
                data.put("referralName", "Not specified!");

            }
            new GuestService().subscribe(data);
        }
    }

    private List<String[]> readCSV(File file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1);
        List<String[]> allRows = reader.readAll();

        return allRows;
    }
}
