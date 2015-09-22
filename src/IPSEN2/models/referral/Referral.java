package IPSEN2.models.referral;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class Referral {
    private int referralID;
    private String name;

    public Referral(int referralID) {
        this.referralID = referralID;
        this.name = null;
    }

    public Referral(int referralID, String name) {
        this.referralID = referralID;
        this.name = name;
    }

    public boolean checkIfOnlyID(){
        return name == null;
    }

    //GETTERS
    public int getReferralID() {
        return referralID;
    }

    public String getName() {
        return name;
    }

    //SETTERS
    public void setReferralID(int referralID) {
        this.referralID = referralID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
