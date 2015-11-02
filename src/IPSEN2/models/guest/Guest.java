package IPSEN2.models.guest;

import IPSEN2.models.address.Address;
import IPSEN2.models.referral.Referral;
import javafx.beans.property.SimpleBooleanProperty;

public class Guest {

    private int id;

    private String gender;
    private String lastName;
    private String prefix;
    private String firstName;
    private SimpleBooleanProperty attended = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty selected = new SimpleBooleanProperty(false);

    public boolean getSelected() {
        return selected.get();
    }

    //public BooleanProperty attendendProperty() {       return attended;}

    private Address address;

    private String email;

    private Referral referral;
    private String notes;

    public Guest() {

    }

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

    public Guest(String gender, String lastName, String firstName) {
        this.gender = gender;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Guest(String name, String email) {
        this.lastName = name;
        this.email = email;
        this.gender = "M";
    }

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

    public boolean checkIfOnlyID() {
        return this.gender == null && this.lastName == null && this.prefix== null && this.firstName == null && this.address == null && this.email == null && this.referral == null && this.notes == null;
    }

    //GETTERS
    public Boolean getAttended() {return attended.get();}

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Referral getReferral() {
        return referral;
    }

    public String getNotes() {
        return notes;
    }

    public Address getAddress() {
        return address;
    }

    //SETTERS
    public void setAttended(boolean attended) { this.attended = new SimpleBooleanProperty(attended);}

    public void setSelected(boolean selected) {this.selected.set(selected);}


    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}