package IPSEN2.validators.order;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class OrderValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("guestId").toString()) != -1
                && Integer.parseInt(data.get("eventId").toString()) != -1
                && Integer.parseInt(data.get("orderStatusId").toString()) != -1;
    }

}
