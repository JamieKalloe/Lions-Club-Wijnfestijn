package IPSEN2.models.wine;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.merchant.Merchant;

/**
 * The class Wine contains all the data for the wine objects.
 */
public class Wine extends TableViewItem {
    private WineType type;
    private String name;
    private String country;
    private String region;
    private int year;
    private double purchasePrice;
    private double price;
    /**
     * The Merchant.
     */
    public Merchant merchant;
    private String typeName;

    /**
     * Instantiates a new Wine.
     */
    public Wine() {

    }

    /**
     * Instantiates a new Wine.
     *
     * @param wineID the wine id
     */
    public Wine(int wineID) {
        this.id = wineID;
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

    /**
     * Instantiates a new Wine.
     *
     * @param wineID        the wine id
     * @param typeID        the type id
     * @param country       the country
     * @param name          the name
     * @param region        the region
     * @param year          the year
     * @param purchasePrice the purchase price
     * @param price         the price
     */
    public Wine(int wineID, int typeID, String country, String name, String region, int year, double purchasePrice, double price) {
        this.id = wineID;
        this.type = new WineType(typeID);
        this.name = name;
        this.country = country;
        this.region = region;
        this.year = year;
        this.purchasePrice = purchasePrice;
        this.price = price;
    }

    /**
     * Check if only id boolean.
     *
     * @return the boolean
     */
    public boolean checkIfOnlyID() {
        return this.type == null && this.name == null && this.country == null && this.region == null && this.year == 0 && this.purchasePrice == 0 && this.price == 0 && this.merchant == null && this.typeName == null;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
//GETTERS
    public WineType getType() {
        return type;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets purchase price.
     *
     * @return the purchase price
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets merchant.
     *
     * @return the merchant
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets type name.
     *
     * @return the type name
     */
    public String getTypeName() {
        return this.type.getName();
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
//SETTERS
    public void setType(WineType type) {
        this.type = type;
    }


    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets purchase price.
     *
     * @param purchasePrice the purchase price
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets merchant.
     *
     * @param merchant the merchant
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Sets type name.
     *
     * @param typeName the type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}