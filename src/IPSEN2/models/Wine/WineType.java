package IPSEN2.models.wine;

/**
 * Created by Bernd on 22-9-2015.
 */
public class WineType {
    private int id;
    private String name;

    public WineType(int id) {
        this.id = id;
    }

    public WineType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
