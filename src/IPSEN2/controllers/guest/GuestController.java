package IPSEN2.controllers.guest;

import IPSEN2.services.guest.GuestService;

import java.util.HashMap;

public class GuestController {

    private GuestService service;

    public GuestController() {
        this.service = new GuestService();

        HashMap data = new HashMap();
        data.put("email", "hallo.vader@hoofddorp.nl");
        data.put("firstname", "Hallo");
        data.put("lastname", "Vader");
        data.put("prefix", "");
        data.put("gender", "M");
        data.put("notes", "This is a note");
        data.put("zipCode", "1066DE");
        data.put("street", "Duckstraat");
        data.put("houseNumber", "666");
        data.put("country", "Nederland");
        data.put("city", "Hoofddorp");
        data.put("referralName", "Member");

        service.remove(9);
        System.out.println(service.find(8).getFirstname());
    }

}