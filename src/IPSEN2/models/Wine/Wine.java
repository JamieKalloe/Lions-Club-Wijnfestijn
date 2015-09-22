package IPSEN2.models.Wine;

public class Wine {
    private int wineID;
    private String type;
    private String name;
    private String country;
    private int year;
    private double price;
    public WineMerchant merchant;

    public Wine() {

    }

    public Wine (String type, String name, String country, int year, double price) {
        this.type = type;
        this.name = name;
        this.country = country;
        this.year = year;
        this.price = price;
    }

    public void addWine() {

    }

    public void deleteWine() {

    }

    public void editWine() {

    }

    //GETTERS
    public int getWineID() {
        return wineID;
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

    public WineMerchant getMerchant() {
        return merchant;
    }

    //SETTERS
    public void setWineID(int wineID) {
        this.wineID = wineID;
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

    public void setMerchant(WineMerchant merchant) {
        this.merchant = merchant;
    }
}