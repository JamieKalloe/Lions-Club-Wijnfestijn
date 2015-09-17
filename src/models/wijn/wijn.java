package IPSEN2;

public class Wijn {
    private int wijnID;
    private String type;
    private String naam;
    private String landVanHerkomst;
    private int jaar;
    private double flesPrijs;

    public Wijn() {

    }

    public Wijn(String type, String naam, String landVanHerkomst, int jaar, double flesPrijs) {
        this.type = type;
        this.naam = naam;
        this.landVanHerkomst = landVanHerkomst;
        this.jaar = jaar;
        this.flesPrijs = flesPrijs;
    }

    public void toevoegenWijn() {

    }

    public void verwijderenWijn() {

    }

    public void aanpassenWijn() {

    }

    //GETTERS
    public int getWijnID() {
        return wijnID;
    }

    public String getType() {
        return type;
    }

    public String getNaam() {
        return naam;
    }

    public String getLandVanHerkomst() {
        return landVanHerkomst;
    }

    public int getJaar() {
        return jaar;
    }

    public double getFlesPrijs() {
        return flesPrijs;
    }

    //SETTERS
    public void setWijnID(int wijnID) {
        this.wijnID = wijnID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setLandVanHerkomst(String landVanHerkomst) {
        this.landVanHerkomst = landVanHerkomst;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public void setFlesPrijs(double flesPrijs) {
        this.flesPrijs = flesPrijs;
    }
}