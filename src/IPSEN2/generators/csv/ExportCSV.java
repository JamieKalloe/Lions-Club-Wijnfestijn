package IPSEN2.generators.csv;

import IPSEN2.database.Database;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.wine.Wine;
import IPSEN2.repositories.Crudable;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.wine.WineService;
import com.opencsv.CSVWriter;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jamie on 13-10-2015.
 */
public class ExportCSV {

    public void exportWine() throws Exception {
        this.exportTable("wine");
    }

    public void exportGuests() throws Exception {
        this.exportTable("guest");
    }

    private void exportTable(String table) throws Exception {
        File saveFile = this.fileDialog("Export " + table + " to csv", "*.csv").showSaveDialog(null);
        this.writeCSV(saveFile, getData(table));

        System.out.println("Exported table " + table + " to CSV.");
    }

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

    private ArrayList<String[]> getWine() {
        ArrayList<String[]> compatList = new ArrayList<>();
        ArrayList<String> array;

        for(Wine wine : new WineService().all()) {
            array = new ArrayList<>();

            array.add(String.valueOf(wine.getWineID()));
            array.add(String.valueOf(wine.getType().getId()));
            array.add(String.valueOf(wine.getMerchant().getMerchantID()));
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

    //database select table -> resultset.
    private void writeCSV(File file, ResultSet resultSet) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
        writer.writeAll(resultSet, true);
        writer.close();
    }

    private void writeCSV(File file, ArrayList<String[]> dataList) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
        writer.writeAll(dataList, true);
        writer.close();
    }

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
