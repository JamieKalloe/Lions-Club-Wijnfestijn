package IPSEN2.services.order;

import IPSEN2.models.order.WineOrder;
import IPSEN2.repositories.order.WineOrderRepository;
import IPSEN2.services.wine.WineService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 15-10-2015.
 */
public class WineOrderService {
    private WineOrderRepository repository;
    private WineService wineService;

    public WineOrderService() {
        this.repository = new WineOrderRepository();
        this.wineService = new WineService();
    }

    public ArrayList<WineOrder> all() {
        ArrayList<WineOrder> wineOrders = repository.all();
        for(WineOrder wineOrder : wineOrders) {
            if(wineOrder.getWine().checkIfOnlyID()) {
                wineOrder.setWine(wineService.find(wineOrder.getWine().getWineID()));
            }
        }
        return wineOrders;
    }

    public ArrayList<WineOrder> allForOrder(int orderID) {
        ArrayList<WineOrder> wineOrders = repository.find(orderID);
        for(WineOrder wineOrder : wineOrders) {
            wineOrder.setWine(wineService.find(wineOrder.getWine().getWineID()));
        }
        return wineOrders;
    }

    public int create(HashMap data) {
        return repository.create(data);
    }

    public void update(int wineOrderID, HashMap data) {
        repository.update(wineOrderID, data);
    }

    public void delete(int orderID, int wineID) {
        this.repository.delete(orderID, wineID);
    }
}
