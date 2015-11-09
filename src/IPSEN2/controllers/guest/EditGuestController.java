package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 26-09-15.
 */
public class EditGuestController extends ContentLoader implements Initializable{

    @FXML private RadioButton genderRadioM;
    @FXML private RadioButton genderRadioF;
    @FXML private RadioButton referralMemberRadio;
    @FXML private RadioButton referralFriendRadio;
    @FXML private RadioButton referralAdRadio;
    @FXML private TextField firstNameTextField;
    @FXML private TextField prefixTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField houseNumberTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;
    @FXML private Pane submitButton, cancelButton;

    private String gender;
    private int referral;
    private String firstName;
    private String prefix;
    private String lastName;
    private String telephone;
    private String email;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String city;

    private HashMap data;
    private GuestService service;
    private int selectedGuestID;
    private ResourceBundle resources;

    public EditGuestController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
    }

    public void handleCancelButton() {
        addContent(resources.getString("GUESTS"));
    }

    /**
     * Handle submit button.
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
            //referral = referralMemberRadio.getText();
            referral = 1;
        } else if (referralFriendRadio.isSelected()) {
            //referral = referralFriendRadio.getText();
            referral = 2;
        } else if (referralAdRadio.isSelected()) {
            //referral = referralAdRadio.getText();
            referral = 3;
        }

        System.out.println("Ref given: " + referral);

        data = new HashMap();

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
        data.put("referralID", referral);

        if (service.edit(selectedGuestID, data)) {
            addContent(resources.getString("GUESTS"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new GuestService();
        this.resources = resources;
            Guest guest = service.find(selectedGuestID);

            firstNameTextField.setText(guest.getFirstName());
            prefixTextField.setText(guest.getPrefix());
            lastNameTextField.setText(guest.getLastName());

            // Gender radio-buttons
            if (guest.getGender().contentEquals("M")) {
                genderRadioM.setSelected(true);
            } else if (guest.getGender().contentEquals("F")) {
                genderRadioF.setSelected(true);
            }

            // Referral type buttons
            if (guest.getReferral().getReferralID() == 1) {
                referralMemberRadio.setSelected(true);
            } else if (guest.getReferral().getReferralID() == 2) {
                referralFriendRadio.setSelected(true);
            } else if (guest.getReferral().getReferralID() == 3) {
                referralAdRadio.setSelected(true);
            }

            emailTextField.setText(guest.getEmail());
            addressTextField.setText(guest.getAddress().getStreet());
            houseNumberTextField.setText(guest.getAddress().getHouseNumber());
            postalCodeTextField.setText(guest.getAddress().getZipCode());
            cityTextField.setText(guest.getAddress().getCity());

            submitButton.setOnMouseClicked(event -> handleSubmitButton());
            cancelButton.setOnMouseClicked(event -> handleCancelButton());

    }

}
