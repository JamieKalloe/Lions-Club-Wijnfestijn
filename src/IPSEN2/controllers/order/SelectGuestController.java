package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.controllers.handlers.TableViewSelectHandler;
import IPSEN2.controllers.listeners.TableViewListener;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Philip on 28-10-15.
 */
public class SelectGuestController extends ContentLoader implements Initializable, TableViewListener{

    @FXML private TableView tableView;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn firstNameColumn;
    @FXML private TableColumn lastNameColumn;
    @FXML private TableColumn emailColumn;
    @FXML private TableColumn checkBoxColumn;
    @FXML private TableColumn attendedColumn;

    private int selectedGuestID;
    private static ObservableList<Guest> attendeeData;
    private ResourceBundle resources;

    @Override
    public void setSelectedRows(ArrayList selectedRows) {
        // nothing to do
    }

    @Override
    public void setSelectedItem(int selectedItemId) {
        this.selectedGuestID = selectedItemId;
        addContent(new AddOrderController(selectedGuestID), resources.getString("EDIT_ORDER_DIALOG"));
    }

    @Override
    public void openEditMenu() {
        // nothing to do
    }


    private void showTable() {
        new TableViewSelectHandler(tableView, this);

        idColumn.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));

        tableView.setItems(attendeeData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        GuestService service = new GuestService();
        attendeeData = FXCollections.observableArrayList(service.findAttendeesForEvent(eventId));

        showTable();
    }


}
