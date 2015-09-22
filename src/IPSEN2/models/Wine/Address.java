package IPSEN2.models.Wine;

/**
 * Created by Bernd on 22-9-2015.
 */
public class Address {
    private int adressID;
    private String zipCode;
    private String street;
    private int houseNumber;
    private String country;
    private String city;

    public Address() {

    }

    //GETTERS

    public int getAdressID() {
        return adressID;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    //SETTERS

    public void setAdressID(int adressID) {
        this.adressID = adressID;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
