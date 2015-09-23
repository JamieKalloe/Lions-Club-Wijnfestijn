package IPSEN2.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mdbaz on 21-09-2015.
 */
public class Validator {
    public static final int NETHERLANDS = 31;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateDate(String date) {
        return true;
    }

    public boolean validateGender(String gender) {

        Pattern pattern = Pattern.compile("^[mfMF]$");
        Matcher matcher = pattern.matcher(gender);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean validateZipcode(int country, String zipCode) {
        switch(country) {
            case NETHERLANDS :
                Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{3} ?[A-Z]{2}$");
                Matcher matcher = pattern.matcher(zipCode);
                if(matcher.matches()) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean validateHouseNumber(String number) {
        Pattern pattern = Pattern.compile("^[1-9][0-9]*(([-][1-9][0-9]*)|([\\s]?[a-zA-Z]+))?$");
        Matcher matcher = pattern.matcher(number);
        if(matcher.matches()) {
            return true;
        }
        return false;
    }
}
