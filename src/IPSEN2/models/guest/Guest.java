package IPSEN2.models.guest;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.address.Address;
import IPSEN2.models.order.Order;
import IPSEN2.models.referral.Referral;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The type Guest.
 */
public class Guest extends TableViewItem {


    private String gender;
    private String lastName;
    private String prefix;
    private String firstName;
    private Order order;
    private SimpleBooleanProperty attended = new SimpleBooleanProperty(false);

    private Address address;

    private String email;

    private Referral referral;
    private String notes;

    /**
     * Instantiates a new Guest.
     */
    public Guest() {

    }

    /**
     * Instantiates a new Guest.
     *
     * @param id the id
     */
    public Guest(int id) {
        this.id = id;
        this.gender = null;
        this.lastName = null;
        this.prefix = null;
        this.firstName = null;
        this.address = null;
        this.email = null;
        this.referral = null;
        this.notes = null;
    }

    /**
     * Instantiates a new Guest.
     *
     * @param gender    the gender
     * @param lastName  the last name
     * @param firstName the first name
     */
    public Guest(String gender, String lastName, String firstName) {
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * Instantiates a new Guest.
     *
     * @param name  the name
     * @param email the email
     */
    public Guest(String name, String email) {
        this.lastName = name;
        this.email = email;
        this.gender = "M";
    }

    /**
     * Instantiates a new Guest.
     *
     * @param id         the id
     * @param gender     the gender
     * @param lastName   the last name
     * @param prefix     the prefix
     * @param firstName  the first name
     * @param addressID  the address id
     * @param email      the email
     * @param referralID the referral id
     * @param notes      the notes
     */
    public Guest(int id, String gender, String lastName, String prefix, String firstName, int addressID ,String email, int referralID, String notes) {
        this.id = id;
        this.gender = gender;
        this.lastName = lastName;
        this.prefix = prefix;
        this.firstName = firstName;
        this.address = new Address(addressID);
        this.email = email;
        this.referral = new Referral(referralID);
        this.notes = notes;
    }

    /**
     * Check if only id boolean.
     *
     * @return the boolean
     */
    public boolean checkIfOnlyID() {
        return this.gender == null && this.lastName == null && this.prefix== null && this.firstName == null && this.address == null && this.email == null && this.referral == null && this.notes == null;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
//GETTERS
    public Order getOrder() {
        return this.order;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets referral.
     *
     * @return the referral
     */
    public Referral getReferral() {
        return referral;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets attended.
     *
     * @param attended the attended
     */
//SETTERS
    public void setAttended(boolean attended) { this.attended = new SimpleBooleanProperty(attended);}

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets prefix.
     *
     * @param prefix the prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {this.firstName = firstName;}

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets referral.
     *
     * @param referral the referral
     */
    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}