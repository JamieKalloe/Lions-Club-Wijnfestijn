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
    private WineTypeRepository wineTypeRepository;
    private WineValidator validator;

    /**
     * Instantiates a new Wine service.
     */
    public WineService()
    {
        this.validator = new WineValidator();
        this.repository = new WineRepository();
        this.wineTypeRepository = new WineTypeRepository();
    }


    /**
     * Wine array list.
     *
     * @return the array list
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
     * Find wine.
     *
     * @param id the id
     * @return the wine
     */
    public Wine find(int id)
    {
        Wine wine = repository.find(id);
        return wine;
    }

    public int create(HashMap data)
    {
        boolean isValid = this.validator.validate(data);

        if (!isValid)
        {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Wijn invoerfout",
                    "Controleer of alle velden correct zijn ingevuld"
            );

            return -1;
        }

        return repository.create(data);
    }


    /**
     * Edit a wine.
     *
     * @param id   the id
     * @param data the data
     * @return the boolean
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
                        "Controleer of alle velden correct zijn ingevuld"
                );

                return false;
            }

            repository.update(id, data);

            return true;
        }

        return false;
    }

    /**
     * Remove object.
     *
     * @param id the id
     * @return the object
     */
    public Object remove(int id) {
        Wine wine = repository.find(id);

        if (wine != null)
        {
            repository.delete(id);

            return true;
        }

        return false;
    }
}
