package IPSEN2.models.guest;

import IPSEN2.models.address.Address;

public class Guest {
    private int klantID;

    private String gender;
    private String achternaam;
    private String tussenvoegsel;
    private String voornaam;

    private Address adddres;

    private String emailadres;

    private String referentie;
    private int opmerkingID;

    public Guest() {

    }

    public Guest(String gender, String achternaam, String voornaam) {
        this.gender = gender;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
    }

    public Guest(String gender, String achternaam, String voornaam, String straat, String huisnummer, String postcode, String woonplaats, String emailadres) {
        this.gender = gender;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
        this.adddres = new Address(straat, huisnummer, postcode, woonplaats);
        this.emailadres = emailadres;
    }

    public Guest(String gender, String achternaam, String voornaam, String straat, String huisnummer, String postcode, String woonplaats, String emailadres, String referentie, int opmerkingID) {
        this.gender = gender;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
        this.adddres = new Address(straat, huisnummer, postcode, woonplaats);
        this.emailadres = emailadres;
        this.referentie = referentie;
        this.opmerkingID = opmerkingID;
    }

    public void aanmakenKlant() {

    }

    public void verwijderKlant() {

    }

    public void aanpassenKlant() {

    }

    //GETTERS
    public int getKlantID() {
        return this.getKlantID();
    }

    public String getGender() {
        return gender;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getReferentie() {
        return referentie;
    }

    public int getOpmerkingID() {
        return opmerkingID;
    }

    //SETTERS
    public void setKlantID(int klantID) {
        this.klantID = klantID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }
    public void setEmailadres(String emailadres) {
        this.emailadres = emailadres;
    }

    public void setReferentie(String referentie) {
        this.referentie = referentie;
    }

    public void setOpmerkingID(int opmerkingID) {
        this.opmerkingID = opmerkingID;
    }
}