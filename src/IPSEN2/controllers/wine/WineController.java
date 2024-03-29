package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.merchant.MerchantService;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.wine.WineService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Wine controller.
 */
public class WineController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn wineIdColumn;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn countryColumn;
    @FXML private TableColumn regionColumn;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn yearColumn;
    @FXML private TableColumn priceColumn;

    @FXML private TableColumn checkBoxColumn;

    private int selectedWineID;
    private WineService wineService;
    private  ObservableList<TableViewItem> wineData;
    private  ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;

    @FXML private Pane removeButton;

    private ResourceBundle resources;

    /**
     * Handle add button.
     *
     * @throws IOException the io exception
     */
    public void handleAddButton() throws IOException{
        if (new MerchantService().all().size() != 0) {
            addContent(new AddWineController(), resources.getString("EDIT_WINE_DIALOG"));
        } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Toevoegfout",
                    "Er is nog geen wijnhandel toegevoegd."
            );
        }
    }

    /**
     * Handle remove button.
     */
    public void handleRemoveButton() {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> wineService.remove(row));
            wineData = FXCollections.observableArrayList(wineService.all());
            addContent(resources.getString("WINE"));
        } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Verwijderfout",
                    "Er is geen wijn geselecteerd"
            );
        }
    }


    @FXML
    private void importCSVFile() {
        //TODO: delete test code, debug only.
        if (new MerchantService().all().size() != 0) {
        try {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importWine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addContent(resources.getString("WINE"));
    } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Toevoegfout",
                    "Er is nog geen wijnhandel toegevoegd."
            );
        }
    }


    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        this.selectedRows = selectedRows;
    }


    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedWineID = selectedItemId;
    }

    @Override
    public void openEditMenu() {
        if (this.selectedWineID != 0) {
            addContent(new EditWineController(selectedWineID), resources.getString("EDIT_WINE_DIALOG"));
        }
    }

    /**
     * Shows all TableView Items <br>
     * Sets TableViewSelectHandler for TableView Object
     */
    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();
        tableViewSelectHandler.createSelectAllCheckBox();

        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("id"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("typeName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Wine, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Wine, String> param) {
                if (param.getValue() != null && param.getValue().getPrice() != 0) {
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
                    return new SimpleStringProperty("€ " + numberFormat.format(param.getValue().getPrice()).replace(" €", ""));
                }
                return new SimpleStringProperty("€ 0,00");
            }
        });

        tableView.setItems(wineData);
        tableView.setPlaceholder(new Label("Er is geen content om te weergeven"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        ContentLoader.setMainFrameTitle(resources.getString("WINES_TITLE"));
        wineService = new WineService();
        selectedRows = new ArrayList<>();
        wineData = FXCollections.observableArrayList(wineService.all());

        showTable();
    }

}