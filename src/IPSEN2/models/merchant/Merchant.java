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

    /**
     * Instantiates a new Merchant.
     *
     * @param id        the id
     * @param name      the name
     * @param email     the email
     * @param addressId the address id
     */
    public Merchant(int id, String name, String email, int addressId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = new Address(addressId);
    }


    public Merchant(int id){
      this.id = id;
    };


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
