//package IPSEN2.services.wine;
//
//import IPSEN2.models.wine.Wine;
//import IPSEN2.services.address.AddressService;
//import IPSEN2.services.referral.ReferralService;
//
//import java.util.ArrayList;
//
///**
// * Created by Bernd on 24-9-2015.
// */
//public class WineService {
//    private WineRepository repository;
//    private WineValidator validator;
//    private AddressService addressService;
//    private ReferralService referralService;
//
//    public WineService() {
//        this.repository = new WineRepository();
//        this.validator = new WineValidator();
//        this.addressService = new AddressService();
//        this.referralService = new ReferralService();
//
//    }
//
//    public ArrayList<Wine> all() {
//        ArrayList<Wine> wineList = repository.all();
//        for(Wine wine : wineList) {
//            if(wine.getReferral().checkIfOnlyID()) {
//                wine.setReferral(referralService.find(wine.getReferral().getReferralID));
//            }
//        }
//        return wineList;
//    }
//}
