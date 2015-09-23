package IPSEN2.controllers.guest;

import IPSEN2.services.guest.GuestService;

import java.util.HashMap;

public class GuestController {

    private GuestService service;

    public GuestController() {
        this.service = new GuestService();

        HashMap data = new HashMap();
        data.put("email", "hallo.vader@hoofddorp.nl");
        data.put("firstname", "Wat");
        data.put("lastname", "Deze");
        data.put("prefix", "is");
        data.put("gender", "F");
        data.put("notes", "This is a note");
        data.put("zipCode", "1354RT");
        data.put("street", "Wat is deze laan");
        data.put("houseNumber", "4");
        data.put("country", "Nederland");
        data.put("city", "Gekke dorp");
        data.put("referralName", "Member");

        int id = service.subscribe(data);
        System.out.println(service.find(id).getFirstname());
    }

}