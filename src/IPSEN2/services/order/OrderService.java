package IPSEN2.services.order;

import IPSEN2.generators.pdf.InvoiceGenerator;
import IPSEN2.models.order.Order;
import IPSEN2.repositories.order.OrderRepository;
import IPSEN2.services.event.EventService;
import IPSEN2.services.guest.GuestService;
import IPSEN2.services.message.Messaging;
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

    private OrderRepository orderRepository;

    private OrderValidator validator;

    private WineOrderService wineOrderService;

    private OrderStatusService orderStatusService;

    public OrderService()
    {
        this.orderRepository = new OrderRepository();

        this.guestService = new GuestService();
        this.eventService = new EventService();
        this.wineService = new WineService();
        this.wineOrderService = new WineOrderService();
        this.orderStatusService = new OrderStatusService();

        this.validator = new OrderValidator();
    }

    public ArrayList<Order> all()
    {
        ArrayList<Order> orderList = this.orderRepository.all();

        for (Order order : orderList)
        {
            order.setGuest(guestService.find(order.getGuest().getId()));
            order.setEvent(eventService.find(order.getEvent().getId()));
            order.setWineOrders(wineOrderService.allForOrder(order.getId()));
            order.setStatus(orderStatusService.find(order.getStatus().getId()));
        }

        return orderList;
    }

    public Order find(int id)
    {
        Order order = this.orderRepository.find(id);
        order.setGuest(guestService.find(order.getGuest().getId()));
        order.setEvent(eventService.find(order.getEvent().getId()));
        order.setStatus(orderStatusService.find(order.getStatus().getId()));
        order.setWineOrders(wineOrderService.allForOrder(order.getId()));
        //order.setWines(wineService.allWithOrderId(order.getId()));

        return order;
    }

    public int add(HashMap data)
    {
        boolean isValid = this.validator.validate(data);

        if (!isValid)
        {
            int id = this.orderRepository.create(data);
            ArrayList<Integer> wineIDs = (ArrayList<Integer>) data.get("wineIDs");
            ArrayList<Integer> amounts = (ArrayList<Integer>)data.get("amounts");
            for(int i = 0; i < wineIDs.size(); i++) {
                HashMap orderData = new HashMap();
                orderData.put("orderID", id);
                orderData.put("wineID", Integer.parseInt(wineIDs.get(i).toString()));
                orderData.put("amount", Integer.parseInt(amounts.get(i).toString()));
                wineOrderService.create(orderData);
            }
            // Add status
            // Add guest
            try {
                new InvoiceGenerator().generate(this.find(id));
            } catch(Exception e) {
                e.printStackTrace();
            }

            Messaging.getInstance().show(
                    "Foutmelding",
                    "Order invoerfout",
                    "Een van de order velden zijn incorrect ingevuld"
            );

            return -1;
        }

        int id = this.orderRepository.create(data);
        ArrayList<Integer> wineIDs = (ArrayList<Integer>) data.get("wineIDs");
        ArrayList<Integer> amounts = (ArrayList<Integer>) data.get("amounts");
        for (int i = 0; i < wineIDs.size(); i++)
        {
            HashMap orderData = new HashMap();
            orderData.put("orderID", id);
            orderData.put("wineID", wineIDs.get(i));
            orderData.put("amount", amounts.get(i));
            wineOrderService.create(orderData);
        }
        // Add status
        // Add guest
        try
        {
            new InvoiceGenerator().generate(this.find(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return id;
    }

    public boolean edit(int id, HashMap data)
    {
        Order order = this.orderRepository.find(id);

        if (order != null)
        {
            boolean isValid = this.validator.validate(data);

            if (!isValid)
            {
                Messaging.getInstance().show(
                        "Foutmelding",
                        "Order invoerfout",
                        "Een van de order velden zijn incorrect ingevuld"
                );

                return false;
            }

            orderRepository.update(id, data);

            return true;
        }

        return false;
    }

    public boolean remove(int id)
    {
        Order order = this.orderRepository.find(id);

        if (order != null)
        {
            this.orderRepository.delete(order.getId());

            return true;
        }

        return false;
    }

}
