package IPSEN2.models.wine;

import IPSEN2.models.winetype.WineType;

public class Wine {
    private int wineID;
    private WineType wineType;
    private String name;
    private String country;
    private String region;
    private int year;
    private double purchasePrice;
    private double price;
    public WineMerchant merchant;

    public Wine() {

    }

    public Wine (int wineID, int typeID, String country, String name, String region, int year, double purchasePrice, double price) {
        this.wineID = wineID;
        this.wineType = new WineType(typeID);
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

    //GETTERS
    public int getWineID() {
        return wineID -1;
    }

    public WineType getType() {
        return wineType;
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

    //SETTERS
    public void setWineID(int wineID) {
        this.wineID = wineID;
    }

    public void setWineType (WineType wineType) {
        this.wineType = wineType;
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

    public void setType(WineType type_id) {
    }
}