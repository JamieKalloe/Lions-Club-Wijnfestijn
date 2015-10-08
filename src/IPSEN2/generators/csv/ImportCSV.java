package IPSEN2.generators.csv;

import IPSEN2.services.guest.GuestService;
import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jamie on 5-10-2015.
 */
public class ImportCSV {

    //Methods
    public void importGuests() throws Exception{
        File selectedFile = this.fileDialog("Select a guest CSV file", "*.csv").showOpenDialog(null);
        List<String[]> guestsCSV = this.readCSV(selectedFile);

        for(String[] customerData : guestsCSV) {
            HashMap data = new HashMap();

            data.put("email", customerData[9]);
            data.put("firstname", customerData[2]);
            data.put("lastname", customerData[1]);
            data.put("prefix", customerData[3]);
            data.put("gender", customerData[4]);
            data.put("notes", customerData[11]);
            data.put("zipCode", customerData[7]);
            data.put("street", customerData[5]);
            data.put("houseNumber", customerData[6]);
            data.put("country", "Nederland");
            data.put("city", customerData[8]);
            data.put("referralName", customerData[10]);

            new GuestService().subscribe(data);
        }
        System.out.println("Succesfully imported guests.");
    }

    public void importWine() throws Exception {
        File selectedFile = this.fileDialog("Select a wine CSV file", "*.csv").showOpenDialog(null);
        List<String[]> wineCSV = this.readCSV(selectedFile);

        for(String[] wineData : wineCSV) {
            HashMap data = new HashMap();

            data.put("email", wineData[9]);
            data.put("firstname", wineData[2]);
            data.put("lastname", wineData[1]);
            data.put("prefix", wineData[3]);
            data.put("gender", wineData[4]);
            data.put("notes", wineData[11]);
            data.put("zipCode", wineData[7]);
            data.put("street", wineData[5]);
            data.put("houseNumber", wineData[6]);
            data.put("country", "Nederland");
            data.put("city", wineData[8]);
            data.put("referralName", wineData[10]);

            //add wineService subscribe here.
            new GuestService().subscribe(data);
        }
        System.out.println("Succesfully imported wine.");
    }

    private List<String[]> readCSV(File file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1);
        List<String[]> allRows = reader.readAll();

        return allRows;
    }

    private FileChooser fileDialog(String title, String...extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        for(String extension : extensions) {
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter(extension.substring(2).toUpperCase() + " Files", extension)
            );
        }

        return fileChooser;
    }
}
