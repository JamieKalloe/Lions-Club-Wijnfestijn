package IPSEN2;

public class Bestelling {
    private int aantalDozen;
    private int wijnID;
    private int klantID;

    public Bestelling(int aantalDozen, int wijnID, int klantID) {
        this.aantalDozen = aantalDozen;
        this.wijnID = wijnID;
        this.klantID = klantID;
    }

    //GETTERS
    public int getAantalDozen() {
        return this.aantalDozen;
    }

    public int getWijnID() {
        return this.wijnID;
    }

    public int getKlantID() {
        return this.klantID;
    }

    //SETTERS
    public void setAantalDozen(int aantalDozen) {
        this.aantalDozen = aantalDozen;
    }

    public void setWijnID(int wijnID) {
        this.wijnID = wijnID;
    }

    public void setKlantID(int klantID) {
        this.klantID = klantID;
    }
}