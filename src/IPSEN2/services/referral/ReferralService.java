package IPSEN2.services.referral;

import IPSEN2.models.referral.Referral;
import IPSEN2.repositories.referral.ReferralRepository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class ReferralService {
    private ReferralRepository repository;

    public ReferralService() {
        this.repository = new ReferralRepository();
    }

    public ArrayList<Referral> all() {
        return new ArrayList<>();
    }

    public Referral find(int id) {
        Referral referral = repository.find(id);
        return referral;
    }

    public void create(HashMap data) {

    }

    public void update(int id, HashMap data) {

    }

    public void delete(int id) {

    }
}
