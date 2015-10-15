package IPSEN2.services.order;

import IPSEN2.models.order.OrderStatus;
import IPSEN2.repositories.order.OrderStatusRepository;

import java.util.ArrayList;

/**
 * Created by mdbaz on 15-10-2015.
 */
public class OrderStatusService {
    private OrderStatusRepository repository;

    public OrderStatusService() {
        this.repository = new OrderStatusRepository();
    }

    public ArrayList<OrderStatus> all() {
        return repository.all();
    }

    public OrderStatus find(int id) {
        return repository.find(id);
    }

}
