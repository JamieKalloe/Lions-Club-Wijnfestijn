package IPSEN2.services.mail;

import IPSEN2.models.guest.Guest;
import IPSEN2.models.mail.Mail;
import IPSEN2.models.mail.MailFactory;
import IPSEN2.models.mail.MailMessage;
import IPSEN2.models.mail.MailType;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.order.OrderService;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

/**
 * The type Mail service.
 */
public class MailService {

    private final String propertyHost = "mail.smtp.host";
    private final String propertyPort = "mail.smtp.port";
    private final String propertyUser = "mail.smtp.user";
    private final String properyPass = "mail.smtp.pwd";
    private final String propertyAuth = "mail.smtp.auth";
    private final String propertyStartTls = "mail.smtp.starttls.enable";
    private final String propertySSL = "mail.smtp.EnableSSL.enable";
    private final String transportType = "smtp";

    private final String sender = "ipsen2groep1@hotmail.com";
    private final String password = "hsLeiden";

    private Properties mailProperties;
    private final String smtpHost = "smtp.live.com";
    private final String smtpUsername = "ipsen2groep1@hotmail.com";
    private final String smtpPassword = "hsLeiden";
    private final int smtpPort = 587;
    private Mail mail;
    private MailType mailType;
    private GuestService guestService;

    /**
     * Instantiates a new Mail service.
     */
    public MailService() {
        this.mailProperties = new Properties();

        mailProperties.put(this.propertyHost, this.smtpHost);
        mailProperties.put(this.propertyPort, String.valueOf(this.smtpPort));
        mailProperties.put(this.propertyUser, this.smtpUsername);
        mailProperties.put(this.properyPass, this.smtpPassword);
        mailProperties.put(this.propertyAuth, "true");
        mailProperties.put(this.propertyStartTls, "true");
        mailProperties.put(this.propertySSL, "true");

    }

    /**
     * Send.
     *
     * @param mail the mail
     * @throws Exception the exception
     */
    public void send(Mail mail) throws Exception {

        this.mail = mail;
        Session session = Session.getInstance(mailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpUsername, smtpPassword);
                    }
                });

        session.setDebug(true);

        MailMessage message;

        if(mail.getAttachment() == null || mail.getAttachment().isEmpty()) {
            message = new MailMessage(
                    session,
                    this.smtpUsername,
                    mail.getRecipient(),
                    mail.getSubject(),
                    mail.getContent()
            );
        } else {
            message = new MailMessage(
                    session,
                    this.smtpUsername,
                    mail.getRecipient(),
                    mail.getSubject(),
                    mail.getContent(),
                    mail.getAttachment()
            );
        }

        Transport transport = session.getTransport(this.transportType);
        transport.connect(this.smtpHost, this.smtpPort, this.smtpUsername, this.smtpPassword);
        transport.sendMessage(message, message.getAllRecipients());

        System.out.println("MailMessage was succesfuly sent.");
        transport.close();
    }

    /**
     * Gets mail type.
     *
     * @param mailType the mail type
     * @return the mail type
     */
    public MailType getMailType(String mailType) {
        switch (mailType) {
            case "Bedanken":
                this.mailType = MailType.THANK;
                break;
            case "Uitnodiging":
                this.mailType = MailType.EVENT;
                break;
            case "Factuur":
                this.mailType = MailType.INVOICE;
                break;
            case "Factuur herrinnering" :
                this.mailType = MailType.REMINDER;
                break;
            case "Wijnhandel" :
                this.mailType = MailType.MERCHANT;
                break;

        }
        return this.mailType;
    }

    /**
     * Gets mail.
     *
     * @param selectedID the selected id
     * @param mailType   the mail type
     * @param receiverId the receiver id
     * @return the mail
     */
    public Mail getMail(int selectedID, MailType mailType, int receiverId) {

        try {

            if (receiverId == 3) {
                OrderService orderService = new OrderService();
                Guest guest = orderService.find(selectedID).getGuest();
                guest.setOrder(orderService.find(selectedID));
                mail = new MailFactory(guest).generate(mailType);
            } else if (receiverId == 2){
                if (mailType == MailType.INVOICE || mailType == MailType.REMINDER){
                    return null;
                }
                mail = new MailFactory(new GuestService().find(selectedID)).generate(mailType);
            } else if (receiverId == 1) {
                if (mailType == MailType.INVOICE || mailType == MailType.REMINDER){
                    return null;
                }
                mail = new MailFactory(selectedID).generate(mailType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail;
    }
}