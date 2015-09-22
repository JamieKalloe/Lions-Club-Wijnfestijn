package IPSEN2.models.Wine;

public class Wine {
    private int wijnID;
    private String type;
    private String name;
    private String country;
    private int year;
    private double price;

    public Wine() {

    }

    public Wine (String type, String name, String country, int year, double price) {
        this.type = type;
        this.name = name;
        this.country = country;
        this.year = year;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    //SETTERS
    public void setWijnID(int wijnID) {
        this.wijnID = wijnID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}