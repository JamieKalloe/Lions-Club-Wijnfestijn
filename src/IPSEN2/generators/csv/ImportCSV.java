package IPSEN2.generators.csv;

import IPSEN2.services.attendee.AttendeeService;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.merchant.MerchantService;
import IPSEN2.services.wine.WineService;
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

    /**
     * Import guests.
     *
     * @param eventID the event id
     * @throws Exception the exception
     */
//Methods
    public void importGuests(int eventID) throws Exception{
        File selectedFile = this.fileDialog("Select a guest CSV file", "*.csv").showOpenDialog(null);
        List<String[]> guestsCSV = this.readCSV(selectedFile);

        if (guestsCSV != null) {
            for (String[] customerData : guestsCSV) {
                HashMap data = new HashMap();

                data.put("firstname", customerData[4]);
                data.put("prefix", customerData[6]);
                data.put("lastname", customerData[5]);
                data.put("email", customerData[3]);
                data.put("gender", customerData[7]);
                data.put("notes", customerData[8]);
                data.put("zipCode", customerData[9]);
                data.put("street", customerData[10]);
                data.put("houseNumber", customerData[11]);
                data.put("country", customerData[12]);
                data.put("city", customerData[13]);
                data.put("referralName", customerData[2]);

                int id = new GuestService().subscribe(data);
                HashMap attendeeData = new HashMap();
                attendeeData.put("guestID", id);
                attendeeData.put("eventID", eventID);
                new AttendeeService().create(attendeeData);

            }
            System.out.println("Succesfully imported guests.");
        }
    }

    /**
     * Import wine.
     *
     * @throws Exception the exception
     */
    public void importWine() throws Exception {
        File selectedFile = this.fileDialog("Select a wine CSV file", "*.csv").showOpenDialog(null);
        List<String[]> wineCSV = this.readCSV(selectedFile);

        if (wineCSV != null) {
            for (String[] wineData : wineCSV) {
                HashMap data = new HashMap();

                data.put("name", wineData[3]);
                data.put("country", wineData[4]);
                data.put("region", wineData[8].replace("'", "''"));
                data.put("year", wineData[5]);
                data.put("typeId", wineData[1]);
                data.put("price", wineData[6]);
                data.put("merchantId", new MerchantService().all().get(0).getId());

                new WineService().subscribe(data);
            }
            System.out.println("Succesfully imported wine.");
        }
    }
    /**
     * Reads CSV file
     *
     * @param file file object to read
     */
    private List<String[]> readCSV(File file) throws IOException{

        CSVReader reader;
        List<String[]> allRows = null;
        try {
            reader = new CSVReader(new FileReader(file), ';', '"', 1);
            allRows = reader.readAll();
            reader.close();

        } catch  (NullPointerException e) {
            System.out.print("No file selected");
        }


        return allRows;
    }

    /**
     * Opens file chooser
     *
     * @return returns file chooser with selected file
     */
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
