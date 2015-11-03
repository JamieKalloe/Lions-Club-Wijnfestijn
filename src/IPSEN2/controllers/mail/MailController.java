package IPSEN2.controllers.mail;

import IPSEN2.ContentLoader;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.mail.Mail;
import IPSEN2.models.mail.MailFactory;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.mail.MailService;
import IPSEN2.services.merchant.MerchantService;
import IPSEN2.services.order.OrderService;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Philip on 01-11-15.
 */
public class MailController extends ContentLoader implements Initializable{

    @FXML private ListView<String> listView;
    @FXML private TextArea mailTextArea;
    @FXML private Pane submitButton, cancelButton;

    private ArrayList<Integer> selectedIDs;
    private String selectedMailType;
    private MailService mailService;
    private ArrayList<String> mailTypes;
    private GuestService guestService;
    private MerchantService merchantService;
    private Mail mail;
    private int selectedID;
    private boolean isMerchant = false;

    public MailController(ArrayList<Integer> selectedIDs) {
        this.selectedIDs = selectedIDs;
    }

    public MailController(ArrayList<Integer> selectedMerchantIDs, boolean isMerchant) {
        this.selectedIDs = selectedMerchantIDs;
        this.isMerchant = isMerchant;
    }


    private void handleSubmitButton() throws Exception {

        for (Integer id : selectedIDs) {
            if (isMerchant) {
                Merchant merchant = merchantService.find(id);
                new MailService().send(new MailFactory(merchant).generate(mailService.getMailType(selectedMailType)));
            } else {
//                Guest guest = guestService.find(id);
                Guest guest = new OrderService().find(id).getGuest();
                guest.setOrder(new OrderService().find(id));
                new MailService().send(new MailFactory(guest).generate(mailService.getMailType(selectedMailType)));
            }
            try {



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        addContent(GUESTS);
    }

    private void handleCancelButton() {
        if (isMerchant) {
            addContent(MERCHANT);
        } else {
            addContent(GUESTS);
        }
    }

    private void handleListView(Event event) {
        selectedMailType = ((ListView) event.getSource()).getSelectionModel().getSelectedItem().toString();
        if (isMerchant) {
            mail = (mailService.getMail(selectedIDs.get(0), mailService.getMailType(selectedMailType), false));
        } else {
        mail = (mailService.getMail(selectedIDs.get(0), mailService.getMailType(selectedMailType), true));}
        mailTextArea.setText(mail.getContent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        merchantService = new MerchantService();
        guestService = new GuestService();
        mailService = new MailService();
        mailTypes = new ArrayList<>(Arrays.asList("Bedanken", "Uitnodiging", "Factuur", "Factuur herrinnering", "Wijnhandel"));
        listView.setItems(FXCollections.observableArrayList(mailTypes));
        listView.setOnMouseClicked(event -> handleListView(event));

        submitButton.setOnMouseClicked(event -> {
            try {
                handleSubmitButton();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnMouseClicked(event -> handleCancelButton());
    }
}
