package IPSEN2.controllers.guest;

import IPSEN2.services.guest.GuestService;

public class GuestController {

    private GuestService service;

    public GuestController() {
        this.service = new GuestService();
    }

}