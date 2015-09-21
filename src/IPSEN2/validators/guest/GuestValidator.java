package IPSEN2.validators.guest;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;
import java.util.HashMap;

public class GuestValidator extends Validator implements Validatable {
    public boolean validate(HashMap data) {
        return validateEmail(data.get("email").toString()) &&
               validateGender(data.get("sex").toString()) &&
               validateZipcode(NETHERLANDS, data.get("zipCode").toString())
               && validateHouseNumber(data.get("houseNumber").toString());
    }
}
