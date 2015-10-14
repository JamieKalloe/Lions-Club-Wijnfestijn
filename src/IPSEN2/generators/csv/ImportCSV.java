package IPSEN2.generators.csv;

import IPSEN2.services.guest.GuestService;
import IPSEN2.services.wine.WineService;
import com.opencsv.CSVReader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

            data.put("name", wineData[3]);
            data.put("country", wineData[4]);
            data.put("region", wineData[8].replace("'", "''"));
            data.put("year", wineData[5]);
            data.put("type_id", wineData[1]);
            data.put("price", wineData[6]);

            //check for null or empty values.

            new WineService().subscribe(data);
        }
        System.out.println("Succesfully imported wine.");
    }

    private List<String[]> readCSV(File file) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(file), ';', '"', 1);
        List<String[]> allRows = reader.readAll();
//        List<String[]> allRows = new ArrayList<>();
//
//        String[] nextLine;
//        while((nextLine = reader.readNext()) != null) {
//            allRows.add(nextLine);
//        }


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
