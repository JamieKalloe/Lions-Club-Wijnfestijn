package IPSEN2.models.address;

/**
 * Created by mdbaz on 21-09-2015.
 */
public class Address {
    private String straat;
    private String huisnummer; //Huisnummer is een String ivm huisnummers die letters erachter kunnen hebben
    private String postcode;
    private String woonplaats;

    public Address(String street, String houseNumber, String zipCode, String city) {
        this.straat = street;
        this.huisnummer = houseNumber;
        this.postcode = zipCode;
        this.woonplaats = city;
    }

    //Getters
    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    //Setters
    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }
}
