package IPSEN2.controllers.wine;

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
public class EditWineController extends ContentLoader implements Initializable{

    @FXML private TextField wineNameTextField;
    @FXML private TextField countryNameNameTextField;
    @FXML private TextField regionNameNameTextField;
    @FXML private TextField yearNameTextField;
    @FXML private TextField typeNameTextField;
    @FXML private TextField priceNameTextField;

    @FXML private Pane submitButton, cancelButton;

    private int selectedWineID;

   public EditWineController(int selectedWineID) {
        this.selectedWineID = selectedWineID;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
