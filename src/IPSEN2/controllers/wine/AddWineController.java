package IPSEN2.controllers.wine;

import IPSEN2.ContentLoader;
import IPSEN2.models.wine.WineType;
import IPSEN2.repositories.wine.WineTypeRepository;
import IPSEN2.services.merchant.MerchantService;
import IPSEN2.services.wine.WineService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class AddWineController extends ContentLoader implements Initializable {

    private WineService service;


    @FXML
    private RadioButton typeWit, typeRood, typeRose, typeCava;

    @FXML
    private Pane cancelButton, submitButton;

    @FXML private TextField wineNameTextField;
    @FXML private TextField countryNameTextField;
    @FXML private TextField regionNameTextField;
    @FXML private TextField yearTextField;
    @FXML private TextField priceTextField;

    private String name;
    private String country;
    private String region;
    private int year;
    private double price;
    private int type;
    private ArrayList<WineType> types;
    private HashMap data;
    private ResourceBundle resources;



    public AddWineController() {

    }

    /**
     * Handle cancel button.
     */
    @FXML
    public void handleCancelButton() {
        addContent(resources.getString("WINE"));
    }


    /**
     * Handle submit button.
     */
    public void handleSubmitButton() {

        name = wineNameTextField.getText();
        country = countryNameTextField.getText();
        region = regionNameTextField.getText();
        year = Integer.parseInt(yearTextField.getText());
        price = Double.parseDouble(priceTextField.getText());

        if (typeWit.isSelected()) {
            type = 1;
        } else if (typeRood.isSelected()) {
            type = 2;
        } else if (typeCava.isSelected()) {
            type = 3;
        } else if (typeRose.isSelected()) {
            type = 4;
        }

        data = new HashMap();

        data.put("name", name);
        data.put("country", country);
        data.put("region", region.replace("'", "''"));
        data.put("year", year);
        data.put("typeId", type);
        data.put("merchantId", new MerchantService().all().get(0).getId());
        data.put("price", price);

        service.create(data);

        addContent(resources.getString("WINE"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        service = new WineService();

        WineTypeRepository wineTypeRepository = new WineTypeRepository();
        types = wineTypeRepository.all();

        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());

    }
}
