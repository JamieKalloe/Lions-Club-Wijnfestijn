package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class EditOrderController extends ContentLoader implements Initializable{

    @FXML
    private TextField wineNameTextField, countryNameNameTextField,
            regionNameNameTextField, yearNameTextField, typeNameTextField,
            priceNameTextField;

    private int selectedWineID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}