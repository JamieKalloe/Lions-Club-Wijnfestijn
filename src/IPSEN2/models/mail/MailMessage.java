package IPSEN2.models.mail;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;
import sun.management.snmp.util.MibLogger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * The type Mail message.
 */
public class MailMessage extends MimeMessage {

    /**
     * Instantiates a new Mail message.
     *
     * @param session   the session
     * @param sender    the sender
     * @param recipient the recipient
     * @param subject   the subject
     * @param content   the content
     * @throws Exception the exception
     */
    public MailMessage(Session session, String sender, String recipient, String subject, String content) throws Exception{
        super(session);

        this.setFrom(new InternetAddress(sender));
        this.setSubject(subject);
        this.setText(content);
        this.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }

    /**
     * Instantiates a new Mail message.
     *
     * @param session    the session
     * @param sender     the sender
     * @param recipient  the recipient
     * @param subject    the subject
     * @param content    the content
     * @param attachment the attachment
     * @throws Exception the exception
     */
    public MailMessage(Session session, String sender, String recipient, String subject, String content, String attachment) throws Exception {
        super(session);

        this.setFrom(new InternetAddress(sender));
        this.setSubject(subject);
        this.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        BodyPart messageContent = new javax.mail.internet.MimeBodyPart();
        messageContent.setText(content);

        BodyPart messageAttachment = new javax.mail.internet.MimeBodyPart();
        DataSource fileSource = new FileDataSource(attachment);
        messageAttachment.setDataHandler(new DataHandler(fileSource));
        messageAttachment.setFileName(attachment);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageContent);
        multipart.addBodyPart(messageAttachment);

        this.setContent(multipart);
    }

}