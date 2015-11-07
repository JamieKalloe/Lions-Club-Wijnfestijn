package IPSEN2.models.merchant;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.address.Address;

/**
 * Created by Philip on 02-11-15.
 */
public class Merchant extends TableViewItem {
    private String name;
    private String email;
    private Address address;

    public Merchant(int id, String name, String email, int addressId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = new Address(addressId);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
