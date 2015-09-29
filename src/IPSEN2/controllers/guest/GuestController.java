package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class GuestController extends ContentLoader implements Initializable{

    @FXML private  TableView<Guest> table_view;
    @FXML private TableColumn id;
    @FXML private TableColumn naam;
    @FXML private TableColumn email;
    @FXML private TableColumn telefoon;

    GuestService service;

    public void handleAddButton() throws IOException {
       addContent(EDIT_GUEST_DIALOG);
    }

    public void handleRemoveButton() {
        for (int i =0; i < 100; i++) {
            service.remove(i);
        }  table_view.getItems().setAll(service.all());

    }

    public void cmdAanmakenKlant() {

    }

    public void cmdAanpassenKlant() {

    }

    public void cmdVerwijderenKlant() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContentLoader.setMainFrameTitle(ContentLoader.GUESTS_TITLE);
      //  table.getColumns().setAll(firstDataColumn, secondDataColumn);

        service = new GuestService();

        id.setCellValueFactory(new PropertyValueFactory<Guest, Integer>("guestID"));
        naam.setCellValueFactory(new PropertyValueFactory<Guest, String>("firstname"));
        email.setCellValueFactory(new PropertyValueFactory<Guest, String>("email"));
        table_view.getItems().setAll(service.all());


     //   email.prefWidthProperty().bind(table_view.widthProperty().multiply(3));

      //  table_view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
}