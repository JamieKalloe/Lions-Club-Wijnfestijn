package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class EditWineController extends ContentLoader implements Initializable{

    @FXML private TextField wineNameTextField;
    @FXML private TextField countryNameTextField;
    @FXML private TextField regionNameTextField;
    @FXML private TextField yearTextField;
    @FXML private SplitMenuButton wineTypeButton;
    @FXML private TextField priceTextField;

    @FXML private Pane submitButton, cancelButton;

    private int selectedWineID;

   public EditWineController(int selectedWineID) {
       this.selectedWineID = selectedWineID;
    }

    @FXML
    public void handleCancelButton() {
        addContent(WINE);
    }

    @FXML
    private void handleSubmitButton() {
        addContent(WINE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wineNameTextField.setText("test");
        countryNameTextField.setText("test");
        regionNameTextField.setText("test");
        yearTextField.setText("test");
        wineTypeButton.setText("test");
        priceTextField.setText("test");

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
