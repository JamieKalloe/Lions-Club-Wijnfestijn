package IPSEN2.services.event;

import IPSEN2.models.event.Event;
import IPSEN2.repositories.event.EventRepository;
import IPSEN2.services.address.AddressService;
import IPSEN2.services.message.Messaging;
import IPSEN2.validators.event.EventValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 08-10-2015.
 */
public class EventService {
    private EventRepository eventRepository;
    private AddressService addressService;
    private EventValidator validator;

    public EventService()
    {
        this.validator = new EventValidator();
        this.eventRepository = new EventRepository();
        this.addressService = new AddressService();
    }

    public ArrayList<Event> all()
    {
        ArrayList<Event> eventList = eventRepository.all();
        for (Event event : eventList)
        {
            if (event.getAddress().checkIfOnlyID())
            {
                event.setAddress(addressService.find(event.getAddress().getAddressID()));
            }
        }
        return eventList;
    }

    public Event find(int id)
    {
        Event event = eventRepository.find(id);
        if (event.getAddress().checkIfOnlyID())
        {
            event.setAddress(addressService.find(event.getAddress().getAddressID()));
        }
        return event;
    }

    public int add(HashMap data)
    {
        Integer addressId = this.addressService.create(data);

        if (addressId == -1)
        {
            return -1;
        }

        data.put("addressID", addressService.create(data));
        boolean isValid = this.validator.validate(data);

        if (! isValid)
        {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Evenement invoerfout",
                    "Een van de evenement velden zijn niet of incorrect ingevuld"
            );

            return -1;
        }

        return this.eventRepository.create(data);
    }

    public boolean edit(int id, HashMap data)
    {
        Event event = eventRepository.find(id);

        if (event != null)
        {
            data.put("addressId", addressService.create(data));

            boolean isValid = this.validator.validate(data);

            if (!isValid)
            {
                Messaging.getInstance().show(
                        "Foutmelding",
                        "Evenement invoerfout",
                        "Een van de evenement velden zijn niet of incorrect ingevuld"
                );

                return false;
            }

            this.eventRepository.update(id, data);

            return true;
        }

        return false;
    }

    public boolean remove(int id)
    {
        Event event = eventRepository.find(id);

        if (event != null)
        {
            eventRepository.delete(id);

            return true;
        }

        return false;
    }
}
