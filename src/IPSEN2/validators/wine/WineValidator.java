package IPSEN2.validators.wine;

import IPSEN2.validators.Validatable;
import IPSEN2.validators.Validator;

import java.util.HashMap;

/**
 * The Wine validator.
 * Date of creation: 12-10-15.
 *
 * @author Thomas Neuteboom
 */
public class WineValidator extends Validator implements Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
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
