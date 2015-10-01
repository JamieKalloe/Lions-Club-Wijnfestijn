package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class AddOrderController extends ContentLoader implements Initializable {

    @FXML
    private TextField wineNameTextField, countryNameNameTextField,
            regionNameNameTextField, yearNameTextField, typeNameTextField,
            priceNameTextField;

    @FXML
    private Pane cancelButton, submitButton;

//    public AddWineController(int selectedWineID) {
//        this.selectedWineID = selectedWineID;
//    }

    @FXML
    public void handleCancelButton() {
        addContent(WINE);
    }

    public void handleSubmitButton() { addContent(WINE); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
