package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Philip on 28-10-15.
 */
public class SelectWineController extends ContentLoader implements Initializable{

    @FXML private TableView<Wine> table_view;
    @FXML private TableColumn wineIdColumn;
    @FXML private TableColumn wineNameColumn;
    @FXML private TableColumn countryColumn;
    @FXML private TableColumn regionColumn;
    @FXML private TableColumn typeColumn;
    @FXML private TableColumn yearColumn;
    @FXML private TableColumn priceColumn;
    @FXML private TableColumn checkBoxColumn;

    private WineService wineService;
    private  ObservableList<Wine> wineData;
    private  ArrayList<String> selectedRows;
    private int selectedWineID;
    private int selectedGuestID;

    @FXML private Pane cancelButton, submitButton;

    public SelectWineController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
    }

    public void handleSubmitButton(){
        if (selectedRows.size() != 0) {
        addContent(new AddOrderController(selectedGuestID, selectedRows), EDIT_ORDER_DIALOG); }
        else {
            handleCancelButton();
        }
    }

    public void handleCancelButton() {
        addContent(new AddOrderController(selectedGuestID), EDIT_ORDER_DIALOG);
    }

    private Callback createCheckBoxCellCallBack() {
        Callback checkBoxCellCallBack = new Callback<TableColumn.CellDataFeatures<Wine, CheckBox>, ObservableValue<CheckBox>>() {

            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Wine, CheckBox> cellDataFeatures) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(cellDataFeatures.getValue().getSelected());
                checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> observableValue,
                                                         Boolean oldValue, Boolean newValue) -> {
                    cellDataFeatures.getValue().setSelected(newValue.booleanValue());

                    selectedWineID = cellDataFeatures.getValue().getWineID();
                    if (newValue.booleanValue()) {
                        selectedRows.add(selectedWineID + "");
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedWineID + ""));
                        selectedWineID = 0;
                    }
                });
                return new SimpleObjectProperty(checkBox);
            }
        };
        return  checkBoxCellCallBack;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wineService = new WineService();
        selectedRows = new ArrayList<>();

        submitButton.setOnMouseClicked(event -> handleSubmitButton());


        cancelButton.setOnMouseClicked(event -> handleCancelButton());

        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("wineID"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("typeName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));



        wineData = FXCollections.observableArrayList(wineService.all());
        wineData.forEach(wine -> wine.setSelected(false));
        table_view.setItems(wineData);
    }
}
