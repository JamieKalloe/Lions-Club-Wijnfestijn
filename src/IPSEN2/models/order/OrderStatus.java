package IPSEN2.models.order;

/**
 * The Order.
 * Date of creation: 07-10-15.
 *
 * @author Thomas Neuteboom
 */
public class OrderStatus {

    private int id;

    private String name;

    /**
     * Instantiates a new Order status.
     *
     * @param id the id
     */
    public OrderStatus(int id) {
        this.id = id;
    }

    /**
     * Instantiates a new Order status.
     *
     * @param id   the id
     * @param name the name
     */
    public OrderStatus(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

}
