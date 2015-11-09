package IPSEN2.generators.csv;

import IPSEN2.models.guest.Guest;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.wine.WineService;
import com.opencsv.CSVWriter;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jamie on 13-10-2015.
 */
public class ExportCSV {

    /**
     * Export wine.
     *
     * @throws Exception the exception
     */
    public void exportWine() throws Exception {
        this.exportTable("wine");
    }

    /**
     * Export guests.
     *
     * @throws Exception the exception
     */
    public void exportGuests() throws Exception {
        this.exportTable("guest");
    }

    private void exportTable(String table) throws Exception {
        File saveFile = this.fileDialog("Export " + table + " to csv", "*.csv").showSaveDialog(null);
        this.writeCSV(saveFile, getData(table));

        System.out.println("Exported table " + table + " to CSV.");
    }

    /**
     * Create merchant csv file.
     *
     * @return the file
     * @throws Exception the exception
     */
    public File createMerchantCSV() throws Exception{
        String fileName = System.getProperty("user.dir") + "/src/IPSEN2/invoice/"
                + new SimpleDateFormat("YYYY-MM-dd").format(new Date()) + ".csv";
        File merchantFile = new File(fileName);
        this.writeCSV(merchantFile, getData("wine"));

        System.out.println("Succesfully made the merchant file.");

        return merchantFile;
    }

    /**
     * @return returns data of database table
     */
    private ArrayList<String[]> getData(String table) {
        ArrayList<String[]> dataList = new ArrayList<>();
        switch (table) {
            case "guest":
                dataList = this.getGuests();
                break;

            case "wine":
                dataList = this.getWine();
                break;

            default:
                break;
        }

        return dataList;
    }

    /**
     * @return returns all wines inside database as ArrayList with String objects
     */
    private ArrayList<String[]> getWine() {
        ArrayList<String[]> compatList = new ArrayList<>();
        ArrayList<String> array;

        for(Wine wine : new WineService().all()) {
            array = new ArrayList<>();

            array.add(String.valueOf(wine.getId()));
            array.add(String.valueOf(wine.getType().getId()));
            array.add(String.valueOf(wine.getMerchant().getId()));
            array.add(String.valueOf(wine.getName()));
            array.add(String.valueOf(wine.getCountry()));
            array.add(String.valueOf(wine.getYear()));
            array.add(String.valueOf(wine.getPrice()));
            array.add(String.valueOf(wine.getPurchasePrice()));
            array.add(String.valueOf(wine.getRegion()));

            String[] guestData = new String[array.size()];
            compatList.add(array.toArray(guestData));
        }

        return compatList;
    }

    /**
     * @return returns all guests inside database as ArrayList with String objects
     */
    private ArrayList<String[]> getGuests() {
        ArrayList<String[]> compatList = new ArrayList<>();
        ArrayList<String> array;

        for(Guest guest : new GuestService().all()) {
            array = new ArrayList<>();
            array.add(String.valueOf(guest.getId()));
            array.add(String.valueOf(guest.getAddress().getAddressID()));
            array.add(String.valueOf(guest.getReferral().getReferralID()));
            array.add(String.valueOf(guest.getEmail()));
            array.add(String.valueOf(guest.getFirstName()));
            array.add(String.valueOf(guest.getLastName()));
            array.add(String.valueOf(guest.getPrefix()));
            array.add(String.valueOf(guest.getGender()));
            array.add(String.valueOf(guest.getNotes()));
            array.add(String.valueOf(guest.getAddress().getZipCode()));
            array.add(String.valueOf(guest.getAddress().getStreet()));
            array.add(String.valueOf(guest.getAddress().getHouseNumber()));
            array.add(String.valueOf(guest.getAddress().getCountry()));
            array.add(String.valueOf(guest.getAddress().getCity()));

            String[] guestData = new String[array.size()];
            compatList.add(array.toArray(guestData));
        }

        return compatList;
    }

    /**
     * writes CSV file
     *
     * @param file file object to write
     * @param resultSet resultset object to write
     */
    private void writeCSV(File file, ResultSet resultSet) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
        writer.writeAll(resultSet, true);
        writer.close();
    }

    /**
     * writes CSV file
     *
     * @param file file object to write
     * @param dataList  data list to write
     */
    private void writeCSV(File file, ArrayList<String[]> dataList) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
        writer.writeAll(dataList, true);
        writer.close();
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
                    new FileChooser.ExtensionFilter(extension.substring(2).toUpperCase() + " Files", extension)
            );
        }

        return fileChooser;
    }
}
