package IPSEN2;

public class Factuur {
    private int factuurNummer;
    private int debiteurNummer;
    private String factuurDatum;

    public Factuur() {

    }

    public Factuur(int factuurNummer, int debiteurNummer, String factuurDatum) {
        this.factuurNummer = factuurNummer;
        this.debiteurNummer = debiteurNummer;
        this.factuurDatum = factuurDatum;
    }

    public void makenFactuur() {

    }

    public void makenWijnhandelFactuur() {

    }

    //GETTERS
    public int getFactuurNummer() {
        return factuurNummer;
    }

    public int getDebiteurNummer() {
        return debiteurNummer;
    }

    public String getFactuurDatum() {
        return factuurDatum;
    }

    //SETTERS
    public void setFactuurNummer(int factuurNummer) {
        this.factuurNummer = factuurNummer;
    }

    public void setDebiteurNummer(int debiteurNummer) {
        this.debiteurNummer = debiteurNummer;
    }

    public void setFactuurDatum(String factuurDatum) {
        this.factuurDatum = factuurDatum;
    }
}