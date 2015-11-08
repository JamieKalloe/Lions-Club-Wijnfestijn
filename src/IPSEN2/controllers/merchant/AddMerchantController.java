package IPSEN2.controllers.merchant;

import IPSEN2.ContentLoader;
import IPSEN2.services.merchant.MerchantService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-11-15.
 */
public class AddMerchantController extends ContentLoader implements Initializable{

    @FXML private Pane submitButton, cancelButton;

    @FXML private TextField nameTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField houseNumberTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField emailTextField;

    private ResourceBundle resources;
    private MerchantService merchantService;

    @FXML
    private void handleCancelButton() {
        addContent(resources.getString("MERCHANT"));
    }

    @FXML
    private void handleSubmitButton() {
        HashMap data = new HashMap();
        data.put("name", nameTextField.getText());
        data.put("email", emailTextField.getText());
        data.put("zipCode", zipCodeTextField.getText());
        data.put("street", addressTextField.getText());
        data.put("houseNumber", houseNumberTextField.getText());
        data.put("country", "Nederland");
        data.put("city", cityTextField.getText());

        int id = merchantService.create(data);
        if (id != -1) {
            addContent(resources.getString("MERCHANT"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        this.merchantService = new MerchantService();
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
    }
}
