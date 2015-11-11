package IPSEN2.services.guest;

import IPSEN2.models.guest.Guest;
import IPSEN2.repositories.guest.GuestRepository;
import IPSEN2.services.address.AddressService;
import IPSEN2.services.message.Messaging;
import IPSEN2.services.referral.ReferralService;
import IPSEN2.validators.guest.GuestValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Guest service.
 */
public class GuestService {
    private GuestRepository repository;
    private GuestValidator validator;
    private AddressService addressService;
    private ReferralService referralService;

    /**
     * Instantiates a new Guest service.
     */
    public GuestService()
    {
        this.repository = new GuestRepository();
        this.validator = new GuestValidator();
        this.addressService = new AddressService();
        this.referralService = new ReferralService();
    }

    /**
     * All array list.
     *
     * @return the array list
     */
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

    /**
     * Find guest.
     *
     * @param id the id
     * @return the guest
     */
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

    /**
     * Subscribe int.
     *
     * @param data the data
     * @return the int
     */
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

    /**
     * Edit boolean.
     *
     * @param id   the id
     * @param data the data
     * @return the boolean
     */
    public boolean edit(int id, HashMap data)
    {
        Guest guest = this.repository.find(id);

        if (guest != null)
        {

            int addressID = guest.getAddress().getAddressID();
            data.put("addressID", addressID);

            new AddressService().update(addressID, data);

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

    /**
     * Remove boolean.
     *
     * @param id the id
     * @return the boolean
     */
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

    /**
     * Send event mail.
     *
     * @param guest the guest
     */
    public void sendEventMail(Guest guest)
    {

    }

    /**
     * Send invoice mail.
     *
     * @param guest the guest
     */
    public void sendInvoiceMail(Guest guest)
    {

    }

    /**
     * Send merchant mail.
     *
     * @param guest the guest
     */
    public void sendMerchantMail(Guest guest)
    {

    }

    /**
     * Send reminder mail.
     *
     * @param guest the guest
     */
    public void sendReminderMail(Guest guest)
    {

    }

    /**
     * Send thank mail.
     *
     * @param guest the guest
     */
    public void sendThankMail(Guest guest)
    {

    }

    /**
     * Find attendees for event array list.
     *
     * @param eventID the event id
     * @return the array list
     */
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

    /**
     * Remove as attendee.
     *
     * @param guestID the guest id
     * @param eventID the event id
     */
    public void removeAsAttendee(int guestID, int eventID)
    {
        repository.deleteKeyFromTable("attendee", "guest_id=" + guestID + " AND event_id=" + eventID);
    }
}
