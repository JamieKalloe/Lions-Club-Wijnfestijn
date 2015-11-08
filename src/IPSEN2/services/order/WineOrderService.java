package IPSEN2.services.order;

import IPSEN2.models.order.WineOrder;
import IPSEN2.repositories.order.WineOrderRepository;
import IPSEN2.services.wine.WineService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Wine Order service.
 * Date of creation: 15-10-15.
 *
 * @author Mike Bazuin
 */
public class WineOrderService {

    private WineOrderRepository repository;
    private WineService wineService;

    /**
     * Instantiates a new Wine order service.
     */
    public WineOrderService()
    {
        this.repository = new WineOrderRepository();
        this.wineService = new WineService();
    }

    /**
     * Get all wine orders.
     *
     * @return the orders
     */
    public ArrayList<WineOrder> all()
    {
        ArrayList<WineOrder> wineOrders = repository.all();
        for (WineOrder wineOrder : wineOrders)
        {
            if (wineOrder.getWine().checkIfOnlyID())
            {
                wineOrder.setWine(wineService.find(wineOrder.getWine().getId()));
            }
        }
        return wineOrders;
    }

    /**
     * Get all orders using order id.
     *
     * @param orderID the order id
     * @return the orders
     */
    public ArrayList<WineOrder> allForOrder(int orderID)
    {
        ArrayList<WineOrder> wineOrders = repository.find(orderID);
        for (WineOrder wineOrder : wineOrders)
        {
            wineOrder.setWine(wineService.find(wineOrder.getWine().getId()));
        }
        return wineOrders;
    }

    /**
     * Create order.
     *
     * @param data the data
     * @return the int
     */
    public int create(HashMap data)
    {
        return repository.create(data);
    }

    /**
     * Update order.
     *
     * @param wineOrderID the wine order id
     * @param data        the data
     */
    public void update(int wineOrderID, HashMap data)
    {
        repository.update(wineOrderID, data);
    }

    /**
     * Delete order.
     *
     * @param orderID the order id
     * @param wineID  the wine id
     */
    public void delete(int orderID, int wineID)
    {
        this.repository.delete(orderID, wineID);
    }

}
