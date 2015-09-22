package IPSEN2.services.address;

import IPSEN2.models.address.Address;
import IPSEN2.repositories.address.AddressRepository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class AddressService {
    private AddressRepository repository;

    public AddressService() {
        repository = new AddressRepository();
    }

    public ArrayList<Address> all() {
        return new ArrayList<Address>();
    }

    public Address find(int id) {
        Address address = repository.find(id);
        return address;
    }

    public Object create(HashMap data) {
        return true;
    }

    public Object update(int id, HashMap data) {
        return true;
    }

    public Object delete(int id) {
        return true;
    }
}
