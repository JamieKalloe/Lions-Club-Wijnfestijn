package IPSEN2.validators;

import java.util.HashMap;

/**
 * The Event validator.
 * Date of creation: 21-09-2015.
 *
 * @author Mike Bazuin
 */

public interface Validatable {

    /**
     * Validate the input data.
     *
     * @param data the data
     * @return the boolean
     */
    boolean validate(HashMap data);

}
