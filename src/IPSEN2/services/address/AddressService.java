package IPSEN2.services.address;

import IPSEN2.models.address.Address;
import IPSEN2.repositories.address.AddressRepository;
import IPSEN2.validators.address.AddressValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class AddressService {
    private AddressRepository repository;
    private AddressValidator validator;

    public AddressService() {
        repository = new AddressRepository();
        validator = new AddressValidator();
    }

    public ArrayList<Address> all() {
        return new ArrayList<Address>();
    }

    public Address find(int id) {
        Address address = repository.find(id);
        return address;
    }

    public int create(HashMap data) {
        boolean isValid = validator.validate(data);
        if(isValid) {
            int exists = repository.exists(data);
            if(exists == 0) {
                return repository.create(data);
            }
            else{
                return exists;
            }
        }
        return -1;
    }

    public Object update(int id, HashMap data) {
        Address address = repository.find(id);
        if(address != null) {
            boolean isValid = validator.validate(data);
            if(isValid) {
                repository.update(id, data);
            }
        }
        return true;
    }

    public Object delete(int id) {
        repository.delete(id);
        return true;
    }
}
