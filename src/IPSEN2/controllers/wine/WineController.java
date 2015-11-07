package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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


    /**
     * Handle add button.
     *
     * @throws IOException the io exception
     */
    public void handleAddButton() throws IOException{
        addContent(new AddWineController(), EDIT_WINE_DIALOG);
    }

    /**
     * Handle remove button.
     */
    public void handleRemoveButton() {
        if (selectedRows.size() != 0) {
            selectedRows.forEach(row -> wineService.remove(row));
        }

        wineData = FXCollections.observableArrayList(wineService.all());
        addContent(WINE);
    }


    @FXML
    private void importCSVFile() {
        //TODO: delete test code, debug only.
        try {
            ImportCSV importCSV = new ImportCSV();
            importCSV.importWine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addContent(WINE);
    }

    private void createSelectAllCheckBox() {
        selectAllCheckBox = new CheckBox();
        boolean selected = selectAllCheckBox.isSelected();
        if (selected) {
            selectedRows.clear();
        }

        wineData.forEach(wine -> {
            wine.setSelected(selected);
            if (selected) {
                selectedRows.add(wine.getId());
            } else {
                selectedRows.clear();
            }
        });
        tableView.refresh();
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
            addContent(new EditWineController(selectedWineID), EDIT_WINE_DIALOG);
        }
    }

    @Override
    public void showToolTip() {

    }

    @Override
    public void hideToolTip() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
        wineService = new WineService();
        selectedRows = new ArrayList<>();
        wineData = FXCollections.observableArrayList(wineService.all());

        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();

        createSelectAllCheckBox();

        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("id"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("typeName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));

        tableView.setItems(wineData);

        tableView.setPlaceholder(new Label("Er is geen content om te weergeven"));
    }

}