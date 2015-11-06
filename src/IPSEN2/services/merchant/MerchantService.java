package IPSEN2.services.merchant;

import IPSEN2.models.merchant.Merchant;
import IPSEN2.repositories.merchant.MerchantRepository;
import IPSEN2.services.address.AddressService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philip on 02-11-15.
 */
public class MerchantService {

    private MerchantRepository repository;
    private AddressService addressService;

    public MerchantService() {
        this.repository = new MerchantRepository();
        this.addressService = new AddressService();
    }

    public ArrayList<Merchant> all() {
        ArrayList<Merchant> merchantList = repository.all();

        for (Merchant merchant : merchantList) {
            merchant.setAddress(addressService.find(merchant.getAddress().getAddressID()));
        }

        return merchantList;
    }

    public Merchant find(int id) {
        Merchant merchant = repository.find(id);
        if(merchant.getAddress().checkIfOnlyID()) {
            merchant.setAddress(addressService.find(merchant.getAddress().getAddressID()));
        }

        return merchant;
    }

    public int create(HashMap data) {

        int addressId = addressService.create(data);

        if (addressId == -1)
        {
            return -1;
        }

        data.put("addressID", addressService.create(data));
        return repository.create(data);
    }

    public boolean edit(int id, HashMap data) {
        Merchant merchant = repository.find(id);
        if(merchant != null) {
            if(new AddressService().update(merchant.getAddress().getAddressID(), data)) {
                data.put("addressID", merchant.getAddress().getAddressID());
                repository.update(id, data);

                return true;
            }
        }
        return false;
    }

    public boolean remove(int id) {
        Merchant merchant = repository.find(id);
        if(merchant != null) {
            repository.delete(id);
        }
        return true;
    }

}
