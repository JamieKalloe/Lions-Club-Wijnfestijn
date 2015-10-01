package IPSEN2.services.wine;

import IPSEN2.models.wine.Wine;
import IPSEN2.repositories.wine.WineRepository;
import IPSEN2.services.referral.ReferralService;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 24-9-2015.
 */
public class WineService {
    private WineRepository repository;
    private ReferralService referralService;


    public WineService() {
        this.repository = new WineRepository();
        this.referralService = new ReferralService();

    }

    /**
     * Ophalen van de wijnlijst
     *
     * @return
     */
    public ArrayList<Wine> all() {
        ArrayList<Wine> wineList = repository.all();
        return wineList;
    }

    /**
     * Zoeken van een specifieke wijn, aan de hand van de wijnID
     *
     * @param id
     * @return
     */
    public Wine find(int id) {
        Wine wine = repository.find(id);
        return wine;
    }

    /**
     * Aanpassen van een bestaande wijn, aan de hand van de wijnID
     *
     * @param id
     * @param data
     * @return
     */
    public boolean edit(int id, HashMap data) {
        Wine wine = repository.find(id);
        if(wine != null) {
            repository.update(id, data);
            return true;
        }
        return false;
    }

    /**
     * Verwijderen van een bestaande wijn, aan de hand van de wijnID
     *
     * @param id
     * @return
     */
    public Object remove(int id) {
        Wine wine = repository.find(id);
        if(wine != null) {
            repository.delete(id);
        }
        return true;
    }
}