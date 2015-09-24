package IPSEN2.models.guest;

import IPSEN2.models.address.Address;
import IPSEN2.models.referral.Referral;

public class Guest {

    private int guestID;

    private String gender;
    private String lastname;
    private String prefix;
    private String firstname;

    private Address address;

    private String email;

    private Referral referral;
    private String notes;

    public Guest() {

    }

    public Guest(String gender, String lastname, String firstname) {
        this.gender = gender;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Guest(int guestID, String gender, String lastname, String prefix, String firstname, int addressID ,String email, int referralID, String notes) {
        this.guestID = guestID;
        this.gender = gender;
        this.lastname = lastname;
        this.prefix = prefix;
        this.firstname = firstname;
        this.address = new Address(addressID);
        this.email = email;
        this.referral = new Referral(referralID);
        this.notes = notes;
    }

    //GETTERS
    public int getGuestID() {
        return guestID;
    }

    public String getGender() {
        return gender;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getFirstname() {
        return firstname;
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
    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

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