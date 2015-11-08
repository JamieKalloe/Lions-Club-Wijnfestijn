package IPSEN2.validators.address;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * The Address validator.
 * Date of creation: 12-10-15.
 *
 * @author Thomas Neuteboom
 */
public class AddressValidator extends Validator implements Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
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
