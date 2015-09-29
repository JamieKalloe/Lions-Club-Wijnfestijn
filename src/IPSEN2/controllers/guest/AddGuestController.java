package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 24-09-15.
 */
public class AddGuestController extends ContentLoader implements Initializable {

    private GuestService service;

    @FXML private TextField firstNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField lastNameTextField;

    private String firstName;
    private String email;
    private String lastName;
    private HashMap data;

    @FXML
    public void handleCancelButton() {
        addContent(GUESTS);
    }

    public void handleSubmitButton() {

        firstName = firstNameTextField.getText();
        email = emailTextField.getText();
        lastName = lastNameTextField.getText();

        data = new HashMap();
        data.put("firstname", firstName);
        data.put("email", email);
        data.put("gender", "m");
        data.put("lastname", lastName);
        data.put("prefix", "");
        data.put("notes", "This is a note");
        data.put("zipCode", "1354RT");
        data.put("street", "Wat is deze laan");
        data.put("houseNumber", "4");
        data.put("country", "Nederland");
        data.put("city", "Gekke dorp");
        data.put("referralName", "Member");
        service.subscribe(data);

        addContent(GUESTS);
    }

    public void handleEditButton() {
        firstName = firstNameTextField.getText();
        email = emailTextField.getText();
        lastName = lastNameTextField.getText();

        HashMap data = new HashMap();
        data.put("firstname", firstName);
        data.put("email", email);
        data.put("gender", "m");
        data.put("lastname", lastName);
        data.put("prefix", "");
        data.put("notes", "This is a note");
        data.put("zipCode", "1354RT");
        data.put("street", "Wat is deze laan");
        data.put("houseNumber", "4");
        data.put("country", "Nederland");
        data.put("city", "Gekke dorp");
        data.put("referralName", "Member");
        service.edit(62, data);
        addContent(GUESTS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new GuestService();
        Guest guest = service.find(62);
        firstNameTextField.setText(guest.getFirstname());
        lastNameTextField.setText(guest.getLastname());
        emailTextField.setText(guest.getEmail());
    }
}
