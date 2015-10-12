package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 24-09-15.
 */
public class AddGuestController extends ContentLoader implements Initializable {

    private GuestService service;

    @FXML private TextField firstNameTextField;
    @FXML private TextField prefixTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField houseNumberTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;
    //@FXML private TextField referralMemberTextField;

    @FXML private Pane cancelButton, submitButton;

    private String firstName;
    private String prefix;
    private String lastName;
    private String telephone;
    private String email;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String city;
    //private String referralMemberTextField;
    private HashMap data;

    @FXML
    public void handleCancelButton() {
        addContent(GUESTS);
    }

    public void handleSubmitButton() {
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        telephone = telephoneTextField.getText();
        email = emailTextField.getText();
        address = addressTextField.getText();
        houseNumber = houseNumberTextField.getText();
        postalCode = postalCodeTextField.getText();
        city = cityTextField.getText();
        //System.out.print(referralMemberTextField.getText());
        data = new HashMap();

        data.put("firstname", firstName);
        data.put("prefix", ".");
        data.put("lastname", lastName);
        data.put("email", email);
        data.put("gender", "M");
        data.put("notes", ".");
        data.put("zipCode", postalCode);
        data.put("street", address);
        data.put("houseNumber", houseNumber);
        data.put("country", "Nederland");
        data.put("city", city);
        data.put("referralName", "Member");
        service.subscribe(data);

        addContent(GUESTS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new GuestService();

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
