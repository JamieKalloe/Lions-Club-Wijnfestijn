package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
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

public class GuestController extends ContentLoader implements Initializable{

    @FXML private  TableView<Guest> table_view;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;


    public int selectedGuestID;
    private GuestService service;

    public void handleAddButton() throws IOException {
       addContent(new AddGuestController(), EDIT_GUEST_DIALOG);

    }

    public void handleRemoveButton() {
        service.remove(selectedGuestID + 1);
        table_view.getItems().setAll(service.all());
    }

    public void handleEditButton() throws IOException{
        if (selectedGuestID != 0) {
            addContent(new EditGuestController(selectedGuestID), EDIT_GUEST_DIALOG);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.GUESTS_TITLE);
        service = new GuestService();


        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("guestID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));

        table_view.getItems().setAll(service.all());
        table_view.setPlaceholder(new Label("Er is geen content om te weergeven"));

        table_view.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());
    }

    private class RowSelectChangeListener implements ChangeListener {

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            try{
            selectedGuestID = table_view.getSelectionModel().getSelectedItem().getGuestID();
            System.out.println(selectedGuestID);} catch (NullPointerException e){
                System.out.print("No items in table to select");
                selectedGuestID = 0;
            }

        }
    }
}