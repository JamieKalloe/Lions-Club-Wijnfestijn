package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.models.TableViewItem;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
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

    public SelectWineController(int selectedID, boolean isEdit) {
        this.selectedID = selectedID;
        this.isEdit = isEdit;
    }

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

    private void showTable() {
        TableViewSelectHandler tableViewSelectHandler = new TableViewSelectHandler(tableView, this);
        tableViewSelectHandler.createCheckBoxColumn();

        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("wineID"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("typeName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));

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
