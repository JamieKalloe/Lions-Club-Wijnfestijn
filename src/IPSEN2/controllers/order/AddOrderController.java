package IPSEN2.controllers.order;

import IPSEN2.ContentLoader;
import IPSEN2.services.order.OrderService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-10-15.
 */
public class AddOrderController extends ContentLoader implements Initializable {
    @FXML
    private AnchorPane wineIDandQuantityWrapper, deleteButtonWrapper;

    @FXML
    private Pane cancelButton, submitButton, addWineButton;

    private ArrayList<Pane> wineAndQuantityList;
    private ArrayList<ImageView> deleteButtonList;

    private ArrayList<TextField> wineIDTextFieldList, wineQuantityTextFieldList;
    @FXML
    private TextField customerIDTextField;

    private double yPosition;


    @FXML
    public void handleCancelButton() {
        addContent(ORDER);
    }

    public void handleSubmitButton() {
        HashMap data = new HashMap();
        data.put("guestId", customerIDTextField.getText());
        data.put("eventId", 1);
        data.put("orderStatusId", 1);
        ArrayList<String> wineIDs = new ArrayList<String>();
        ArrayList<String> amounts = new ArrayList<String>();
        wineAndQuantityList.forEach(row -> {
            int index = wineAndQuantityList.indexOf(row);
            wineIDs.add(wineIDTextFieldList.get(index).getText());
            amounts.add(wineQuantityTextFieldList.get(index).getText());
          System.out.println("wine ID: " + wineIDTextFieldList.get(index).getText());
           System.out.println("Quantity: " + wineQuantityTextFieldList.get(index).getText());
        });
        data.put("wineIDs", wineIDs);
        data.put("amounts", amounts);
        new OrderService().add(data);
        addContent(ORDER);
    }

    public void handleAddWineButton() throws IOException {

       createWineIDAndQuanityContainer();

        addWineButton.setLayoutY(addWineButton.getLayoutY() + 50);

        createDeleteButton();

    }

    private void createWineIDAndQuanityContainer() throws IOException{
        Pane wineIDAndQuantityContainer = new Pane();
        TextField wineIDTextField = new TextField();
        Label wineInfoLabel = new Label("Wijnnaam ,soort en streek");
        TextField wineQuantityTextField = new TextField();

        wineIDAndQuantityContainer.setPrefWidth(611);
        wineIDTextField.setPrefWidth(167);
        wineQuantityTextField.setPrefWidth(41);


        wineInfoLabel.setTextFill(Paint.valueOf("#999999"));
        wineInfoLabel.setLayoutX(252);
        wineInfoLabel.setFont(new Font("Roboto", 18));
        wineQuantityTextField.setLayoutX(570);

        wineIDTextFieldList.add(wineIDTextField);
        wineQuantityTextFieldList.add(wineQuantityTextField);



        wineIDAndQuantityContainer.getChildren().addAll(wineIDTextField, wineInfoLabel, wineQuantityTextField);

        if (wineAndQuantityList.size() != 0){
            yPosition += 50;
            wineIDAndQuantityContainer.setLayoutY(yPosition);
        } else {
            yPosition = 0;
            wineIDAndQuantityContainer.setLayoutY(yPosition);

        }

        wineIDandQuantityWrapper.getChildren().add(wineIDAndQuantityContainer);
        wineAndQuantityList.add(wineIDAndQuantityContainer);
    }

    private void createDeleteButton() throws IOException{
        ImageView deleteButton = new ImageView("/IPSEN2/images/DeleteButton2.png");
        deleteButton.setLayoutY(yPosition);
        deleteButton.getStyleClass().add("buttonWithoutHover");

        deleteButtonWrapper.getChildren().add(deleteButton);
        deleteButtonList.add(deleteButton);

        handleDeleteButton(deleteButton);
    }

    private void handleDeleteButton(ImageView deleteButton){
        deleteButton.setOnMouseClicked(event -> deleteRow(event));
    }


    public void deleteRow(MouseEvent event) {
        for(int i = 0; i < deleteButtonList.size(); i++){

            if (event.getSource() == deleteButtonList.get(i)) {
                repositionListItems(i);
                deleteButtonWrapper.getChildren().remove(deleteButtonList.get(i));
                wineIDandQuantityWrapper.getChildren().remove(wineAndQuantityList.get(i));
                deleteButtonList.remove(i);
                wineAndQuantityList.remove(i);
                yPosition -= 50;

            }
        }
    }


    private void repositionListItems(int index) {
        for(int j = index; j < deleteButtonList.size(); j++) {
            if (j > index) {
                deleteButtonWrapper.getChildren().get(j).setLayoutY(deleteButtonWrapper.getChildren().get(j).getLayoutY() - 50);
                wineIDandQuantityWrapper.getChildren().get(j).setLayoutY(wineIDandQuantityWrapper.getChildren().get(j).getLayoutY() - 50);
            }

        }

        addWineButton.setLayoutY(addWineButton.getLayoutY() - 50);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnMouseClicked(event -> handleSubmitButton());
        cancelButton.setOnMouseClicked(event -> handleCancelButton());

        addWineButton.setOnMouseClicked(event -> {
            try {
                handleAddWineButton();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButtonList = new ArrayList<>();
        wineAndQuantityList = new ArrayList<>();
        wineIDTextFieldList = new ArrayList<>();
        wineQuantityTextFieldList = new ArrayList<>();

        try {
           createWineIDAndQuanityContainer();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            createDeleteButton();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
