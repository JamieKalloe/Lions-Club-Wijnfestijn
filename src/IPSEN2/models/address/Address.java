package IPSEN2.models.address;

/**
 * Created by mdbaz on 21-09-2015.
 */
public class Address {
    private int addressID;
    private String street;
    private String houseNumber; //Huisnummer is een String ivm huisnummers die letters erachter kunnen hebben
    private String zipCode;
    private String city;
    private String country;

    public Address(int addressID) {
        this.addressID = addressID;
        this.street = null;
        this.houseNumber = null;
        this.zipCode = null;
        this.city = null;
        this.country = null;
    }

    public Address(int addressID, String street, String houseNumber, String zipCode, String city, String country) {
        this.addressID = addressID;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public boolean checkIfOnlyID() {
        return street == null && houseNumber == null && zipCode == null && city == null && country == null;
    }

    //Getters
    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public int getAddressID() {
        return addressID;
    }

    public String getCountry() {
        return country;
    }

    //Setters
    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
