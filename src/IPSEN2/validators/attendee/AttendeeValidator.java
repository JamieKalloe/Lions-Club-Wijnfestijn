package IPSEN2.validators.attendee;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class AttendeeValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("guestID").toString()) != -1
                && Integer.parseInt(data.get("eventID").toString()) != -1;
    }

}
