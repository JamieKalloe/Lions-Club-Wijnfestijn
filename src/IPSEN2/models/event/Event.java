package IPSEN2.models.event;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.address.Address;

import java.util.Date;

/**
 * Created by mdbaz on 08-10-2015.
 */
public class Event extends TableViewItem {
    private String name;
    private Address address;
    private String street;
    private String city;
    private Date startDate;
    private Date endDate;
    private boolean selected;

    /**
     * Instantiates a new Event.
     */
    public Event() {
        this.id = -1;
        this.name = null;
        this.address = null;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Instantiates a new Event.
     *
     * @param id the id
     */
    public Event(int id) {
        this.id = id;
        this.name = null;
        this.address = null;
        this.startDate = null;
        this.endDate = null;
    }

    /**
     * Instantiates a new Event.
     *
     * @param id        the id
     * @param name      the name
     * @param address   the address
     * @param startDate the start date
     * @param endDate   the end date
     */
    public Event(int id, String name, Address address, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Check if only id boolean.
     *
     * @return the boolean
     */
    public boolean checkIfOnlyID() {
        return this.name == null && this.address == null && this.startDate == null && this.endDate == null;
    }

    //GETTERS

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {return address.getStreet() + " " + address.getHouseNumber();}

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {return address.getCity();}

    public boolean getSelected() {
        return selected;
    }


    /**
     * Sets name.
     *
     * @param name the name
     */
//SETTERS
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
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSelected(boolean selected) {this.selected = selected;}

}
