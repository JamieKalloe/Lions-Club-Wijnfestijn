package IPSEN2.controllers.guest;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.services.guest.GuestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML private TextField telephoneTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField houseNumberTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;
    @FXML private Pane submitButton, cancelButton;

    private String gender;
    private String referral;
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

    public EditGuestController(int selectedGuestID) {
        this.selectedGuestID = selectedGuestID;
    }

    public void handleCancelButton() {
        addContent(GUESTS);
    }

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
        telephone = telephoneTextField.getText();
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
            service.edit(selectedGuestID, data);
            addContent(GUESTS);

           /* Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text("Hi"), new Button("Ok.")).
                    alignment(Pos.CENTER).padding(new Insets(5)).build()));
            dialogStage.show();*/

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        service = new GuestService();


            Guest guest = service.find(selectedGuestID);
            firstNameTextField.setText(guest.getFirstName());
            lastNameTextField.setText(guest.getLastName());
            emailTextField.setText(guest.getEmail());

            firstNameTextField.setText(guest.getFirstName());
            prefixTextField.setText(guest.getPrefix());
            lastNameTextField.setText(guest.getLastName());
            //telephoneTextField.setText(guest.);
            emailTextField.setText(guest.getEmail());
            addressTextField.setText(guest.getAddress().getStreet());
            houseNumberTextField.setText(guest.getAddress().getHouseNumber());
            postalCodeTextField.setText(guest.getAddress().getZipCode());
            cityTextField.setText(guest.getAddress().getCity());

            submitButton.setOnMouseClicked(event -> handleSubmitButton());
            cancelButton.setOnMouseClicked(event -> handleCancelButton());

    }

}
