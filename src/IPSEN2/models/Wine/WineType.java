package IPSEN2.models.Wine;

/**
 * Created by Bernd on 22-9-2015.
 */
public class WineType {
    private int wineID;
    private String name;

    public WineType() {

    }

    //GETTERS
    public int getWineID() {
        return wineID;
    }

    public String getName() {
        return name;
    }

    //SETTERS

    public void setWineID(int wineID) {
        this.wineID = wineID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
