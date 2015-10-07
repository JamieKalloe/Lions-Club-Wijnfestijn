package IPSEN2.models.wine;

/**
 * Created by Bernd on 22-9-2015.
 */
public class WineType {
    private int wineTypeID;
    private String name;

    public WineType() {

    }

    public WineType(int wineTypeID) {
        this.wineTypeID = wineTypeID;
    }

    //GETTERS
    public int getWineTypeID() {
        return wineTypeID;
    }

    public String getName() {
        return name;
    }

    //SETTERS

    public void setWineTypeID(int wineTypeID) {
        this.wineTypeID = wineTypeID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
