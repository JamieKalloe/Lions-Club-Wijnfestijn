package IPSEN2.services.order;

import IPSEN2.models.order.OrderStatus;
import IPSEN2.repositories.order.OrderStatusRepository;

import java.util.ArrayList;

/**
 * The Order service.
 * Date of creation: 15-10-15.
 *
 * @author Mike Bazuin
 */
public class OrderStatusService {

    private OrderStatusRepository repository;

    /**
     * Instantiates a new Order status service.
     */
    public OrderStatusService()
    {
        this.repository = new OrderStatusRepository();
    }

    /**
     * Get all order statuses.
     *
     * @return the array list
     */
    public ArrayList<OrderStatus> all()
    {
        return repository.all();
    }

    /**
     * Find order status.
     *
     * @param id the id
     * @return the order status
     */
    public OrderStatus find(int id)
    {
        return repository.find(id);
    }

}
