package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.generators.csv.ImportCSV;
import IPSEN2.models.wine.Wine;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.wine.WineService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The type Wine controller.
 */
public class WineController extends ContentLoader implements Initializable{

    @FXML private TableView<Wine> table_view;
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
    private static ObservableList<Wine> wineData;
    private static ArrayList<Integer> selectedRows;
    private CheckBox selectAllCheckBox;
    private static boolean selected;
    private static boolean keepCurrentData = false;

    @FXML private Pane removeButton;


    /**
     * Handle add button.
     *
     * @throws IOException the io exception
     */
    public void handleAddButton() throws IOException{
        keepCurrentData = false;

        addContent(new AddWineController(), EDIT_WINE_DIALOG);
    }

    /**
     * Handle remove button.
     */
    public void handleRemoveButton() {
        if(selectedRows.size() != 0) {
            selected = false;

            selectedRows.forEach(row -> {
                System.out.println("removing row: " + row);
                wineService.remove(row);
            });
        } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Verwijderfout",
                    "Er is geen wijn geselecteerd"
            );
        }

        wineData = FXCollections.observableArrayList(wineService.all());
        addContent(WINE);
    }


    /**
     * Open edit wine menu.
     *
     * @throws IOException the io exception
     */
    public void openEditWineMenu(){
        if (selectedWineID != 0) {
            keepCurrentData = false;
            selected = false;
            addContent(new EditWineController(selectedWineID), EDIT_WINE_DIALOG);
        }
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

        keepCurrentData = false;
        addContent(WINE);
    }

    private void setOnTableRowClickedListener() {
        table_view.setRowFactory(table -> {
            TableRow<Wine> row = new TableRow<>();
            row.getStyleClass().add("pane");
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    selectedWineID = row.getTableView().getSelectionModel().getSelectedItem().getWineID();
                    openEditWineMenu();
                }

            });
            return row;
        });
    }

    private void createSelectAllCheckBox() {
        selectAllCheckBox = new CheckBox();
        selectAllCheckBox.setSelected(selected);
        checkBoxColumn.setGraphic(selectAllCheckBox);
        selectAllCheckBox.setOnAction(event -> {
            selected = selectAllCheckBox.isSelected();
            if (selected) {
                selectedRows.clear();
            }
            wineData.forEach(wine -> {
                wine.setSelected(selected);
                if (selected) {
                    selectedRows.add(wine.getWineID());
                } else {
                    selectedRows.clear();
                }
            });

            addContent(WINE);
            selectAllCheckBox.setSelected(selected);
        });
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
                        selectedRows.add(selectedWineID);
                    } else if (!newValue.booleanValue()) {
                        selectedRows.remove(selectedRows.indexOf(selectedWineID));
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
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
        wineService = new WineService();

        if (!keepCurrentData) {
            wineData = FXCollections.observableArrayList(wineService.all());
            selectedRows = new ArrayList<>();
            keepCurrentData = true;
        }

        table_view.setItems(wineData);
        setOnTableRowClickedListener();

        checkBoxColumn.setCellValueFactory(createCheckBoxCellCallBack());
        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("wineID"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("typeName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));


        createSelectAllCheckBox();
        table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));

        setOnTableRowClickedListener();

    }




}