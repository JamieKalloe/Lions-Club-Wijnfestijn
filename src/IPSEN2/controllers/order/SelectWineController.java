package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Philip on 28-10-15.
 */
public class SelectWineController extends ContentLoader implements Initializable, TableViewListener {

    @FXML private TableView<TableViewItem> tableView;
    @FXML private TableColumn wineIdColumn;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn countryColumn;
    @FXML private TableColumn regionColumn;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn yearColumn;
    @FXML private TableColumn priceColumn;
    @FXML private TableColumn checkBoxColumn;

    private WineService wineService;
    private  ObservableList<TableViewItem> wineData;
    private  ArrayList<Integer> selectedRows;
    private int selectedWineID;
    private int selectedID;
    private boolean isEdit;
    private ResourceBundle resources;
    @FXML private Pane cancelButton, submitButton;

    /**
     * Instantiates a new Select wine controller.
     *
     * @param selectedID the selected id
     * @param isEdit     the is edit
     */
    public SelectWineController(int selectedID, boolean isEdit) {
        this.selectedID = selectedID;
        this.isEdit = isEdit;
    }

    /**
     * Handle cancel button.
     */
    public void handleCancelButton() {
        addContent(new AddOrderController(selectedID), resources.getString("EDIT_ORDER_DIALOG"));
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
        if (selectedRows.size() != 0) {
            if (isEdit) {

                addContent(new EditOrderController(selectedID, selectedRows), resources.getString("EDIT_ORDER_DIALOG"));
            } else {
                addContent(new AddOrderController(selectedID, selectedRows), resources.getString("EDIT_ORDER_DIALOG"));

            }
        } else {
            handleCancelButton();
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

        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("Id"));
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        this.wineService = new WineService();
        this.selectedRows = new ArrayList<>();

        wineData = FXCollections.observableArrayList(wineService.all());
        wineData.forEach(wine -> wine.setSelected(false));

        showTable();

        submitButton.setOnMouseClicked(event -> openEditMenu());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
