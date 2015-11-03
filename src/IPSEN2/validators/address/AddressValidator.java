package IPSEN2.validators.address;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class AddressValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return validateHouseNumber(data.get("houseNumber").toString())
                && !data.get("zipCode").toString().isEmpty()
                && !data.get("street").toString().isEmpty()
                && !data.get("country").toString().isEmpty()
                && !data.get("city").toString().isEmpty()
                && validateZipcode(NETHERLANDS, data.get("zipCode").toString());
    }
}
