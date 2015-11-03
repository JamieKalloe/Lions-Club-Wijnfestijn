package IPSEN2.validators.guest;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;
import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class GuestValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return  Integer.parseInt(data.get("addressID").toString()) != -1
                && Integer.parseInt(data.get("referralID").toString()) != -1
                && !data.get("firstname").toString().isEmpty()
                && !data.get("lastname").toString().isEmpty()
                && !data.get("email").toString().isEmpty()
                && validateEmail(data.get("email").toString())
                && validateGender(data.get("gender").toString());
    }
}
