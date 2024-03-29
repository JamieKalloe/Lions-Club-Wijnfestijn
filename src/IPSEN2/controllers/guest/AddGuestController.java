package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.attendee.AttendeeService;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.message.Messaging;
import IPSEN2.validators.guest.GuestValidator;
import IPSEN2.validators.wine.WineValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 24-09-15
 */
public class AddGuestController extends ContentLoader implements Initializable {

    private GuestService service;
    private AttendeeService attendeeService;
    private GuestValidator validator;

    @FXML private RadioButton genderRadioM;
    @FXML private RadioButton genderRadioF;
    @FXML private RadioButton referralMemberRadio;
    @FXML private RadioButton referralFriendRadio;
    @FXML private RadioButton referralAdRadio;
    @FXML private TextField firstNameTextField;
    @FXML private TextField prefixTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField houseNumberTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;

    @FXML private Pane cancelButton, submitButton;

    private String gender;
    private String referral;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String city;
    //private String referralMemberTextField;
    private HashMap data;
    private HashMap attendeeData;
    private ResourceBundle resources;

    /**
     * Handles cancel button
     */
    public void handleCancelButton() {
        addContent(resources.getString("GUESTS"));
    }

    /**
     * Handles submit button
     */
    public void handleSubmitButton() {

        // Check if Male of Female radiobutton is selected
        if (genderRadioM.isSelected()) {
            gender = "M";
        } else if (genderRadioF.isSelected()) {
            gender = "F";
        }

        // Other values are being copied from textBoxes
        firstName = firstNameTextField.getText();
        prefix = prefixTextField.getText();
        lastName = lastNameTextField.getText();
        email = emailTextField.getText();
        address = addressTextField.getText();
        houseNumber = houseNumberTextField.getText();
        postalCode = postalCodeTextField.getText();
        city = cityTextField.getText();

        // Insert the right referral type in the referral variable
        if (referralMemberRadio.isSelected()) {
            referral = referralMemberRadio.getText();
        } else if (referralFriendRadio.isSelected()) {
            referral = referralFriendRadio.getText();
        } else if (referralAdRadio.isSelected()) {
            referral = referralAdRadio.getText();
        }

        data = new HashMap();
        attendeeData = new HashMap();

        data.put("firstname", firstName);
        data.put("prefix", prefix);
        data.put("lastname", lastName);
        data.put("email", email);
        data.put("gender", gender);
        data.put("notes", ".");
        data.put("zipCode", postalCode);
        data.put("street", address);
        data.put("houseNumber", houseNumber);
        data.put("country", "Nederland");
        data.put("city", city);
        data.put("referralName", referral);

        int id = service.subscribe(data);

        if (id != -1) {
            Guest guest = service.find(id);

            attendeeData.put("guestID", guest.getId());
            attendeeData.put("eventID", eventId);
            attendeeData.put("zipCode", guest.getAddress().getZipCode());
            attendeeData.put("street", guest.getAddress().getStreet());
            attendeeData.put("houseNumber", guest.getAddress().getHouseNumber());
            attendeeData.put("country", guest.getAddress().getCountry());
            attendeeData.put("city", guest.getAddress().getCity());
            attendeeData.put("referralName", guest.getReferral());

            attendeeService.create(attendeeData);
            addContent(resources.getString("GUESTS"));

        } else {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Gasten invoerfout",
                    "Een van de gast velden zijn incorrect ingevuld"
            );

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        service = new GuestService();
        attendeeService = new AttendeeService();

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
