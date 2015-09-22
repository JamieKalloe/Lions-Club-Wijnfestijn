package IPSEN2.services.guest;

import IPSEN2.models.guest.Guest;
import IPSEN2.repositories.guest.GuestRepository;
import IPSEN2.services.address.AddressService;
import IPSEN2.services.referral.ReferralService;
import IPSEN2.validators.guest.GuestValidator;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestService {
    private GuestRepository repository;
    private GuestValidator validator;
    private AddressService addressService;
    private ReferralService referralService;

    public GuestService(){
        this.repository = new GuestRepository();
        this.validator = new GuestValidator();
        this.addressService = new AddressService();
        this.referralService = new ReferralService();
    }

    public ArrayList<Guest> all() {
        ArrayList<Guest> guestList = repository.all();
        for(Guest guest : guestList) {
            if(guest.getAddress().checkIfOnlyID()) {
                guest.setAddress(addressService.find(guest.getAddress().getAddressID()));
            }
            if(guest.getReferral().checkIfOnlyID()) {
                guest.setReferral(referralService.find(guest.getReferral().getReferralID()));
            }
        }
        return guestList;
    }

    public Guest find(int id) {
        Guest guest = repository.find(id);
        if(guest.getAddress().checkIfOnlyID()) {
            guest.setAddress(addressService.find(guest.getAddress().getAddressID()));
        }
        if(guest.getReferral().checkIfOnlyID()) {
            guest.setReferral(referralService.find(guest.getReferral().getReferralID()));
        }
        return guest;
    }

    public int subscribe(HashMap data) {
        boolean isValid = validator.validate(data);
        if(isValid) {
            data.put("addressID", addressService.create(data));
            data.put("referralID", referralService.create(data));
            int i = repository.create(data);
            return  i;
        }
        return -1;
    }

    public Object edit(int id, HashMap data) {
        Guest guest = repository.find(id);
        boolean isValid = validator.validate(data);
        if(isValid) {
            repository.update(id, data);
        }
        return true;
    }

    public Object remove(int id) {
        Guest guest = repository.find(id);
        if(guest != null) {
            repository.delete(id);
        }
        return true;
    }
}
