package IPSEN2.generators.csv;

import IPSEN2.database.Database;
import com.opencsv.CSVWriter;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

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

    private ResultSet getData(String table) {
        ResultSet resultSet = Database.getInstance().select(table);

        return resultSet;
    }

    private void writeCSV(File file, ResultSet resultSet) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(file), ';');
        writer.writeAll(resultSet, true);
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
