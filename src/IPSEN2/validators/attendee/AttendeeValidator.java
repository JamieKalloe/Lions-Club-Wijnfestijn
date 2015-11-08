package IPSEN2.validators.attendee;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * The Attendee validator.
 * Date of creation: 12-10-15.
 *
 * @author Thomas Neuteboom
 */
public class AttendeeValidator extends Validator implements Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("guestID").toString()) != -1
                && Integer.parseInt(data.get("eventID").toString()) != -1;
    }

}
