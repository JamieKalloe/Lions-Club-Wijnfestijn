package IPSEN2.controllers.guest;

import IPSEN2.services.guest.GuestService;

import java.util.HashMap;

public class GuestController {

    private GuestService service;

    public GuestController() {
        this.service = new GuestService();

        HashMap data = new HashMap();
        data.put("email", "d.duck@duckstad.nl");
        data.put("firstname", "Donaldinho");
        data.put("lastname", "Dock");
        data.put("prefix", " ");
        data.put("gender", "F");
        data.put("notes", "Vage eend dit zeg");
        data.put("zipCode", "1066DD");
        data.put("street", "Duckstraat");
        data.put("houseNumber", "666");
        data.put("country", "Geen idee");
        data.put("city", "Duckstad");
        data.put("referralName", "Member");

        int id = service.subscribe(data);
        System.out.println(service.find(id).getFirstname());
    }

}