package IPSEN2.models.event;

import IPSEN2.models.address.Address;

import java.util.Date;

/**
 * Created by mdbaz on 08-10-2015.
 */
public class Event {
    private int id;
    private String name;
    private Address address;



    private String street;
    private String city;
    private Date startDate;
    private Date endDate;
    private boolean selected;

    public Event() {
        this.id = -1;
        this.name = null;
        this.address = null;
        this.startDate = null;
        this.endDate = null;
    }

    public Event(int id) {
        this.id = id;
        this.name = null;
        this.address = null;
        this.startDate = null;
        this.endDate = null;
    }

    public Event(int id, String name, Address address, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean checkIfOnlyID() {
        return this.name == null && this.address == null && this.startDate == null && this.endDate == null;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStreet() {return address.getStreet() + " " + address.getHouseNumber();}

    public String getCity() {return address.getCity();}

    public boolean getSelected() {
        return selected;
    }


    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSelected(boolean selected) {this.selected = selected;}

}
