package IPSEN2.models.order;

/**
 * Created by Thomas on 07-10-15.
 */
public class OrderStatus {

    private int id;

    private String name;

    public OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
