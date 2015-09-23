package IPSEN2.services.referral;

import IPSEN2.models.referral.Referral;
import IPSEN2.repositories.Crudable;
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

    public int create(HashMap data) {
        int exists = repository.exists(data);
        if(exists == 0) {
            return repository.create(data);
        }
        else{
            return exists;
        }
    }

    public void update(int id, HashMap data) {
        repository.update(id, data);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
