package IPSEN2.models.klant;

public class Klant {
    private int klantID;

    private String aanhef;
    private String achternaam;
    private String voornaam;

    private String straat;
    private String huisnummer; //Huisnummer is een String ivm huisnummers die letters erachter kunnen hebben
    private String postcode;
    private String woonplaats;

    private String emailadres;

    private String referentie;
    private int opmerkingID;

    public Klant() {

    }

    public Klant(String aanhef, String achternaam, String voornaam) {
        this.aanhef = aanhef;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
    }

    public Klant(String aanhef, String achternaam, String voornaam, String straat, String huisnummer, String postcode, String woonplaats, String emailadres) {
        this.aanhef = aanhef;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.emailadres = emailadres;
    }

    public Klant(String aanhef, String achternaam, String voornaam, String straat, String huisnummer, String postcode, String woonplaats, String emailadres, String referentie, int opmerkingID) {
        this.aanhef = aanhef;
        this.achternaam = achternaam;
        this.voornaam = voornaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
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

    public String getAanhef() {
        return aanhef;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

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

    public void setAanhef(String aanhef) {
        this.aanhef = aanhef;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

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