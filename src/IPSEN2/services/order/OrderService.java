package IPSEN2.services.order;

import IPSEN2.models.order.Order;
import IPSEN2.repositories.order.OrderRepository;
import IPSEN2.repositories.order.OrderStatusRepository;
import IPSEN2.services.event.EventService;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.wine.WineService;
import IPSEN2.validators.order.OrderValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas on 07-10-15.
 */
public class OrderService {

    private GuestService guestService;

    private EventService eventService;

    private WineService wineService;

    private OrderStatusRepository orderStatusRepository;

    private OrderRepository orderRepository;

    private OrderValidator validator;

    public OrderService()
    {
        this.orderRepository = new OrderRepository();
        this.orderStatusRepository = new OrderStatusRepository();

        this.guestService = new GuestService();
        this.eventService = new EventService();
        this.wineService = new WineService();

        this.validator = new OrderValidator();
    }

    public ArrayList<Order> all()
    {
        ArrayList<Order> orderList = this.orderRepository.all();

        for (Order order: orderList)
        {

        }

        return new ArrayList<>();
    }

    public Order find(int id)
    {
        Order order = this.orderRepository.find(id);

        order.setGuest(guestService.find(order.getGuest().getID()));
        order.setEvent(eventService.find(order.getEvent().getId()));
        order.setStatus(orderStatusRepository.find(order.getStatus().getId()));

        //order.setWines(wineService.allWithOrderId(order.getId()));

        return order;
    }

    public int add(HashMap data)
    {
        boolean isValid = this.validator.validate(data);

        if(isValid)
        {
            int id = this.orderRepository.create(data);


            // Add wines to order
            // Add status
            // Add guest

            return id;
        }

        return -1;
    }

    public boolean edit(int id, HashMap data)
    {
        Order order = this.orderRepository.find(id);

        if(order != null)
        {
            boolean isValid = this.validator.validate(data);

            if(isValid)
            {
                orderRepository.update(id, data);
            }

            return true;
        }

        return false;
    }

    public boolean remove(int id)
    {
        Order order = this.orderRepository.find(id);

        if(order != null)
        {
            this.orderRepository.delete(order.getId());
        }

        return true;
    }

}
