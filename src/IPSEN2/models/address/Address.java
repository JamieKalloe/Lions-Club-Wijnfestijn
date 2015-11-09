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

    /**
     * Instantiates a new Address.
     *
     * @param addressID the address id
     */
    public Address(int addressID) {
        this.addressID = addressID;
        this.street = null;
        this.houseNumber = null;
        this.zipCode = null;
        this.city = null;
        this.country = null;
    }

    /**
     * Instantiates a new Address.
     *
     * @param addressID   the address id
     * @param street      the street
     * @param houseNumber the house number
     * @param zipCode     the zip code
     * @param city        the city
     * @param country     the country
     */
    public Address(int addressID, String street, String houseNumber, String zipCode, String city, String country) {
        this.addressID = addressID;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    /**
     * Check if only id boolean.
     *
     * @return the boolean
     */
    public boolean checkIfOnlyID() {
        return street == null && houseNumber == null && zipCode == null && city == null && country == null;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
//Getters
    public String getStreet() {
        return street;
    }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets address id.
     *
     * @return the address id
     */
    public int getAddressID() {
        return addressID;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
//Setters
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets house number.
     *
     * @param houseNumber the house number
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets address id.
     *
     * @param addressID the address id
     */
    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
