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

public class Mail extends MimeMessage {

    public Mail(Session session, String sender, String recipient, String subject, String content) throws Exception{
        super(session);

        this.setFrom(new InternetAddress(sender));
        this.setSubject(subject);
        this.setText(content);
        this.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    }

    public Mail(Session session, String sender, String recipient, String subject, String content, String attachment) throws Exception {
        super(session);

        this.setFrom(new InternetAddress(sender));
        this.setSubject(subject);
        this.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        BodyPart message1 = new javax.mail.internet.MimeBodyPart();
        message1.setText(content);

        BodyPart message2 = new javax.mail.internet.MimeBodyPart();
        DataSource fileSource = new FileDataSource(attachment);
        message2.setDataHandler(new DataHandler(fileSource));
        message2.setFileName(attachment);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(message1);
        multipart.addBodyPart(message2);

        this.setContent(multipart);
    }

}