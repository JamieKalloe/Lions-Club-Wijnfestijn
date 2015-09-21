package IPSEN2.services.guest;

import IPSEN2.models.guest.Guest;
import IPSEN2.repositories.guest.GuestRepository;
import IPSEN2.validators.guest.GuestValidator;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestService {
    private GuestRepository repository;
    private GuestValidator validator;

    public GuestService(){
        this.repository = new GuestRepository();
        this.validator = new GuestValidator();
    }

    public ArrayList<Guest> all() {
        return new ArrayList<Guest>();
    }

    public Guest find(int id) {
        return new Guest();
    }

    public Object create(HashMap data) {
        boolean isValid = validator.validate(data);
        if(isValid) {
            repository.create(data);
        }
        return true;
    }

    public Object update(int id, HashMap data) {
        Guest guest = repository.find(id);
        boolean isValid = validator.validate(data);
        if(isValid) {
            repository.update(id, data);
        }
        return true;
    }

    public Object delete(int id) {
        Guest guest = repository.find(id);
        if(guest != null) {
            repository.delete(id);
        }
        return true;
    }
}
