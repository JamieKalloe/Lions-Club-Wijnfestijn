package IPSEN2.validators.event;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * The Event validator.
 * Date of creation: 12-10-15.
 *
 * @author Thomas Neuteboom
 */
public class EventValidator extends Validator implements Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("addressID").toString()) != -1
                && !data.get("name").toString().isEmpty()
                && !data.get("startDate").toString().isEmpty()
                && !data.get("endDate").toString().isEmpty()
                && this.validateDate(data.get("startDate").toString())
                && this.validateDate(data.get("endDate").toString());
    }

}
