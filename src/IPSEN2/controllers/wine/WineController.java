package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;

import IPSEN2.models.wine.Wine;
import IPSEN2.services.wine.WineService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private WineService service;

    public WineController() {
        this.service = new WineService();
    }

    public void handleAddButton() throws IOException{
        addContent(EDIT_WINE_DIALOG);
    }

    public void handleRemoveButton() {
        service.remove(selectedWineID + 1);
        table_view.getItems().setAll(service.all());

    }

    public void handleEditButton() throws IOException {
        if (selectedWineID != 0) {
            addContent(new EditWineController(selectedWineID), EDIT_WINE_DIALOG);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.WINES_TITLE);
        service = new WineService();

        wineIdColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("wineID"));
        wineNameColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("country"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<Wine, String>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("typeID"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Wine, Integer>("year"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Wine, Double>("price"));
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));

        table_view.getItems().setAll(service.all());
        table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));

        table_view.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
    }


    private class RowSelectChangeListener implements ChangeListener {

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            try{
                selectedWineID = table_view.getSelectionModel().getSelectedItem().getWineID();
                System.out.println(selectedWineID);
            } catch (NullPointerException e){
                System.out.print("No items in table to select");
                selectedWineID = 0;
            }

        }
    }
}