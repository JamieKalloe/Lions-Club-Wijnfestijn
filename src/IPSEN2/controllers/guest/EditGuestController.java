package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 26-09-15.
 */
public class EditGuestController extends ContentLoader implements Initializable{

    @FXML
    private TextField firstNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField lastNameTextField;
    @FXML private Pane submitButton, cancelButton;

    private String firstName;
    private String email;
    private String lastName;
    private HashMap data;
    private GuestService service;
    private int selectedGuestID;

    public EditGuestController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
        System.out.println(selectedGuestID);

    }

    public void handleCancelButton() {
        addContent(GUESTS);
    }

    public void handleSubmitButton() {
        email = emailTextField.getText();
        firstName = firstNameTextField.getText();
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
        service.edit(2, data);
        addContent(GUESTS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service = new GuestService();

        Guest guest = service.find(selectedGuestID);
        firstNameTextField.setText(guest.getFirstName());
        lastNameTextField.setText(guest.getLastName());
        emailTextField.setText(guest.getEmail());

        System.out.println("initialize method");


        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());

//
//        addContent(GUESTS);
    }

}
