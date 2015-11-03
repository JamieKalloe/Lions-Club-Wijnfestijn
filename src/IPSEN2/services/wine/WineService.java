package IPSEN2.services.wine;

import IPSEN2.models.wine.Wine;
import IPSEN2.repositories.wine.WineRepository;
import IPSEN2.repositories.wine.WineTypeRepository;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.referral.ReferralService;
import IPSEN2.validators.wine.WineValidator;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 24-9-2015.
 */
public class WineService {
    private WineRepository repository;
    private ReferralService referralService;
    private WineTypeRepository wineTypeRepository;
    private WineValidator validator;

    public WineService()
    {
        this.validator = new WineValidator();
        this.repository = new WineRepository();
        this.referralService = new ReferralService();
        this.wineTypeRepository = new WineTypeRepository();
    }

    /**
     * Ophalen van de wijnlijst
     *
     * @return
     */
    public ArrayList<Wine> all()
    {
        ArrayList<Wine> wineList = this.repository.all();

        for (Wine wine : wineList)
        {
            wine.setType(this.wineTypeRepository.find(wine.getType().getId()));
        }

        return wineList;
    }

    /**
     * Zoeken van een specifieke wijn, aan de hand van de wijnID
     *
     * @param id
     * @return
     */
    public Wine find(int id)
    {
        Wine wine = repository.find(id);
        return wine;
    }

    public int subscribe(HashMap data)
    {
        boolean isValid = this.validator.validate(data);

        if (!isValid)
        {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Wijn invoerfout",
                    "Een van de wijn velden zijn niet of incorrect ingevuld"
            );

            return -1;
        }

        return repository.create(data);
    }

    /**
     * Aanpassen van een bestaande wijn, aan de hand van de wijnID
     *
     * @param id
     * @param data
     * @return
     */
    public boolean edit(int id, HashMap data)
    {
        Wine wine = repository.find(id);

        if (wine != null)
        {
            boolean isValid = this.validator.validate(data);

            if (!isValid)
            {
                Messaging.getInstance().show(
                        "Foutmelding",
                        "Wijn invoerfout",
                        "Een van de wijn velden zijn niet of incorrect ingevuld"
                );

                return false;
            }

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
    public Object remove(int id)
    {
        Wine wine = repository.find(id);

        if (wine != null)
        {
            repository.delete(id);

            return true;
        }

        return false;
    }
}
