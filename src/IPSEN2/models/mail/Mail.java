package IPSEN2.models.mail;

/**
 * Created by Jamie on 28-9-2015.
 */
public class Mail {

    private String recipient;
    private String subject;
    private String content;
    private String attachment;

    /**
     * Mail object for sending with the mailservice
     *
     * @param recipient  recipient of the mail
     * @param subject    subject of the mail
     * @param content    content (text-based) of the mail
     * @param attachment attachment of the file (direct path to the file).
     */
    public Mail(String recipient, String subject, String content, String attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
    }

    /**
     * Instantiates a new Mail.
     *
     * @param recipient recipient of the mail
     * @param subject   subject of the mail
     * @param content   content (text-based) of the mail
     */
    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    /**
     * returns the recipient as a string
     *
     * @return string recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * returns the subject as a string
     *
     * @return string subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * returns the content as a string
     *
     * @return string content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets attachment.
     *
     * @return the attachment
     */
    public String getAttachment() {
        return attachment;
    }
}
