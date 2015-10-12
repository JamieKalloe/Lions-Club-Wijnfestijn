package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class AddWineController extends ContentLoader implements Initializable {

    @FXML
    private TextField wineNameTextField, countryNameNameTextField,
    regionNameNameTextField, yearNameTextField, typeNameTextField,
    priceNameTextField;

    @FXML
    private SplitMenuButton wineTypeButton;

    @FXML
    private MenuItem typeWit, typeRood, typeRose, typeCava;

    @FXML
    private Pane cancelButton, submitButton;

    private int selectedWineID;

//    public AddWineController(int selectedWineID) {
//        this.selectedWineID = selectedWineID;
//    }

    @FXML
    public void handleWineTypeButton(ActionEvent event) {
        Object wineType = event.getSource();
        if (wineType == typeWit) {
            wineTypeButton.setText("Wit");
        }
    }

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
