package IPSEN2.services.guest;

import IPSEN2.models.guest.Guest;
import IPSEN2.repositories.guest.GuestRepository;
import IPSEN2.services.address.AddressService;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.referral.ReferralService;
import IPSEN2.validators.guest.GuestValidator;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestService {
    private GuestRepository repository;
    private GuestValidator validator;
    private AddressService addressService;
    private ReferralService referralService;

    public GuestService()
    {
        this.repository = new GuestRepository();
        this.validator = new GuestValidator();
        this.addressService = new AddressService();
        this.referralService = new ReferralService();
    }

    public ArrayList<Guest> all()
    {
        ArrayList<Guest> guestList = repository.all();

        for (Guest guest : guestList)
        {
            if (guest.getAddress().checkIfOnlyID())
            {
                guest.setAddress(addressService.find(guest.getAddress().getAddressID()));
            }
            if (guest.getReferral().checkIfOnlyID())
            {
                guest.setReferral(referralService.find(guest.getReferral().getReferralID()));
            }
        }
        return guestList;
    }

    public Guest find(int id)
    {
        Guest guest = repository.find(id);
        if (guest.getAddress().checkIfOnlyID())
        {
            guest.setAddress(addressService.find(guest.getAddress().getAddressID()));
        }
        if (guest.getReferral().checkIfOnlyID())
        {
            guest.setReferral(referralService.find(guest.getReferral().getReferralID()));
        }
        return guest;
    }

    public int subscribe(HashMap data)
    {
        Integer addressId = this.addressService.create(data);

        if (addressId == -1)
        {
            return -1;
        }

        Integer referralId = this.referralService.create(data);

        if (referralId == -1)
        {
            return -1;
        }

        data.put("addressID", addressId);
        data.put("referralID", referralId);

        boolean isValid = this.validator.validate(data);

        if (!isValid)
        {
            Messaging.getInstance().show(
                    "Foutmelding",
                    "Gasten invoerfout",
                    "Een van de gast velden zijn incorrect ingevuld"
            );

            return -1;
        }

        return this.repository.create(data);
    }

    public boolean edit(int id, HashMap data)
    {
        Guest guest = this.repository.find(id);

        if (guest != null)
        {
            data.put("addressID", guest.getAddress().getAddressID());
            data.put("referralID", guest.getReferral().getReferralID());

            boolean isValid = this.validator.validate(data);

            if (!isValid)
            {
                Messaging.getInstance().show(
                        "Foutmelding",
                        "Gasten invoerfout",
                        "Een van de gast velden zijn incorrect ingevuld"
                );

                return true;
            }

            this.repository.update(id, data);

            return true;
        }

        return false;
    }

    public boolean remove(int id)
    {
        Guest guest = repository.find(id);

        if (guest != null)
        {
            repository.delete(id);

            return true;
        }

        return false;
    }

    public void sendEventMail(Guest guest)
    {

    }

    public void sendInvoiceMail(Guest guest)
    {

    }

    public void sendMerchantMail(Guest guest)
    {

    }

    public void sendReminderMail(Guest guest)
    {

    }

    public void sendThankMail(Guest guest)
    {

    }

    public ArrayList<Guest> findAttendeesForEvent(int eventID)
    {
        ArrayList<Guest> guestList = repository.findAllIn("attendee", "guest_id", "event_id=" + eventID);

        for (Guest guest : guestList)
        {
            if (guest.getAddress().checkIfOnlyID())
            {
                guest.setAddress(addressService.find(guest.getAddress().getAddressID()));
            }
            if (guest.getReferral().checkIfOnlyID())
            {
                guest.setReferral(referralService.find(guest.getReferral().getReferralID()));
            }
        }
        return guestList;
    }

    public void removeAsAttendee(int guestID, int eventID)
    {
        repository.deleteKeyFromTable("attendee", "guest_id=" + guestID + " AND event_id=" + eventID);
    }
}
