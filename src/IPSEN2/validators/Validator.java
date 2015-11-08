package IPSEN2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Event validator.
 * Date of creation: 21-09-15.
 *
 * @author Mike Bazuin
 */
public class Validator {

    /**
     * The constant NETHERLANDS.
     */
    public static final int NETHERLANDS = 31;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate date.
     *
     * @param date the date
     * @return the boolean
     */
    public boolean validateDate(String date)
    {
        return true;
    }

    /**
     * Validate gender.
     *
     * @param gender the gender
     * @return the boolean
     */
    public boolean validateGender(String gender)
    {
        Pattern pattern = Pattern.compile("^[mfMF]$");
        Matcher matcher = pattern.matcher(gender);

        if (matcher.matches())
        {
            return true;

        }
        return false;
    }

    /**
     * Validate email.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean validateEmail(String email)
    {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
        {
            return true;

        }
        return false;
    }

    /**
     * Validate zipcode.
     *
     * @param country the country
     * @param zipCode the zip code
     * @return the boolean
     */
    public boolean validateZipcode(int country, String zipCode)
    {
        switch (country)
        {
            case NETHERLANDS:
                Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{3} ?[a-zA-Z]{2}$");
                Matcher matcher = pattern.matcher(zipCode);
                if (matcher.matches())
                {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * Validate house number.
     *
     * @param number the number
     * @return the boolean
     */
    public boolean validateHouseNumber(String number)
    {
        Pattern pattern = Pattern.compile("^[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches())
        {
            return true;

        }
        return false;
    }

}
