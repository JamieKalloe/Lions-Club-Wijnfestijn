package IPSEN2.validators.order;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * The Order validator.
 * Date of creation: 12-10-15.
 *
 * @author Thomas Neuteboom
 */
public class OrderValidator extends Validator implements Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("guestId").toString()) != -1
                && Integer.parseInt(data.get("eventId").toString()) != -1
                && Integer.parseInt(data.get("orderStatusId").toString()) != -1;
    }

}
