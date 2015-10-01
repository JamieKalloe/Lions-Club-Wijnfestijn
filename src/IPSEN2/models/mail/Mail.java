package IPSEN2.models.mail;

import java.util.ArrayList;

/**
 * Created by Jamie on 28-9-2015.
 */
public class Mail {

    private String recipient;
    private String subject;
    private String content;
    private String attachment;

    public Mail(String recipient, String subject, String content, String attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
    }

    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getAttachment() {
        return attachment;
    }
}
