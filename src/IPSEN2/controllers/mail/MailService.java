package IPSEN2.controllers.mail;

import IPSEN2.models.mail.Mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    //Variables
    private final String propertyHost = "mail.smtp.host";
    private final String propertyPort = "mail.smtp.port";
    private final String propertyUser = "mail.smtp.user";
    private final String properyPass = "mail.smtp.pwd";
    private final String propertyAuth = "mail.smtp.auth";
    private final String propertyStartTls = "mail.smtp.starttls.enable";
    private final String propertySSL = "mail.smtp.EnableSSL.enable";
    private final String transportType = "smtp";

    private Properties mailProperties;
    private String smtpHost;
    private String smtpUsername;
    private String smtpPassword;
    private int smtpPort;

    private Session session;

    //Constructor
    public MailService(String host, String username, String password, int port) {
        this.mailProperties = new Properties();
        this.smtpHost = host;
        this.smtpUsername = username;
        this.smtpPassword = password;
        this.smtpPort = port;

        mailProperties.put(this.propertyHost, this.smtpHost);
        mailProperties.put(this.propertyPort, String.valueOf(this.smtpPort));
        mailProperties.put(this.propertyUser, this.smtpUsername);
        mailProperties.put(this.properyPass, this.smtpPassword);
        mailProperties.put(this.propertyAuth, "true");
        mailProperties.put(this.propertyStartTls, "true");
        mailProperties.put(this.propertySSL, "true");
    }

    //Methods
    public void sendMail(Mail mail) throws Exception {
        this.session = Session.getInstance(mailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpUsername, smtpPassword);
                    }
                });

        this.session.setDebug(true);

        Transport transport = session.getTransport(this.transportType);
        transport.connect(this.smtpHost, this.smtpPort, this.smtpUsername, this.smtpPassword);
        transport.sendMessage(mail, mail.getAllRecipients());

        System.out.println("Mail was succesfuly sent.");
        transport.close();
    }

    public Session getSession() {
        return this.session;
    }
}