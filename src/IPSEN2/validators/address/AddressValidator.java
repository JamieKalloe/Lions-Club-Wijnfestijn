package IPSEN2.validators.address;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class AddressValidator extends Validator implements Validatable {
    public boolean validate(HashMap data) {

        return validateHouseNumber(data.get("houseNumber").toString()) && validateZipcode(NETHERLANDS, data.get("zipCode").toString());
    }
}
