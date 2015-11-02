package IPSEN2.validators.event;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by Thomas on 02-11-15.
 */
public class EventValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return  Integer.parseInt(data.get("addressID").toString()) != -1
                && !data.get("name").toString().isEmpty()
                && !data.get("startDate").toString().isEmpty()
                && !data.get("endDate").toString().isEmpty()
                && this.validateDate(data.get("startDate").toString())
                && this.validateDate(data.get("endDate").toString());
    }

}
