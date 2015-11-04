package IPSEN2.validators.wine;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class WineValidator extends Validator implements Validatable {

    public boolean validate(HashMap data)
    {
        return Integer.parseInt(data.get("typeId").toString()) != -1
                && !data.get("name").toString().isEmpty()
                && !data.get("country").toString().isEmpty()
                && !data.get("region").toString().isEmpty()
                && !data.get("year").toString().isEmpty()
                && !data.get("price").toString().isEmpty();
    }

}
