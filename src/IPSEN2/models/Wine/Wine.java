package IPSEN2.models.wine;

public class Wine {
    private int wineID;
    private WineType type;
    private String name;
    private String country;
    private String region;
    private int year;
    private double purchasePrice;
    private double price;
    public WineMerchant merchant;
    private String typeName;

    public Wine() {

    }

    public Wine(int wineID) {
        this.wineID = wineID;
        this.type = null;
        this.name = null;
        this.country = null;
        this.region = null;
        this.year = 0;
        this.purchasePrice = 0;
        this.price = 0;
        this.merchant = null;
        this.typeName = null;
    }

    public Wine (int wineID, int typeID, String country, String name, String region, int year, double purchasePrice, double price) {
        this.wineID = wineID;
        this.type = new WineType(typeID);
        this.name = name;
        this.country = country;
        this.region = region;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.price = price;
    }

    public void addWine() {

    }

    public void deleteWine() {

    }

    public void editWine() {

    }

    public boolean checkIfOnlyID() {
        return this.type == null && this.name == null && this.country == null && this.region == null && this.year == 0 && this.purchasePrice == 0 && this.price == 0 && this.merchant == null && this.typeName == null;
    }

    //GETTERS
    public int getWineID() {
        return wineID -1;
    }

    public WineType getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getPrice() {
        return price;
    }

    public WineMerchant getMerchant() {
        return merchant;
    }

    public String getRegion() {
        return region;
    }

    public String getTypeName() {
        return this.type.getName();
    }

    //SETTERS
    public void setWineID(int wineID) {
        this.wineID = wineID;
    }

    public void setType(WineType type) {
        this.type = type;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMerchant(WineMerchant merchant) {
        this.merchant = merchant;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}