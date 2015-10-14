package IPSEN2.models.wine;

import IPSEN2.models.address.Address;

/**
 * Created by Bernd on 22-9-2015.
 */
public class WineMerchant {
    private int merchantID;
    private Address address;
    private String name;
    private String email;

    public WineMerchant() {

    }

    public WineMerchant(int id) {
        this.merchantID = id;
        this.address = null;
        this.name = null;
        this.email = null;
    }

    //GETTERS
    public int getMerchantID() {
        return merchantID;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    //SETTERS

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
