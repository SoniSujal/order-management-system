package org.example.Order_Management_System.utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtils {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final String NAME_REGEX = "^[A-Za-z ]+$";

    //Function to check if field is empty or not
    public static boolean isFieldEmpty(String value, String fieldName)
    {
        if(value == null || value.trim().isEmpty()) {
            log.error("{} must not be Empty ! ",fieldName);
            return true;
        }
        return false;
    }

    public static boolean isInvalidNameFormat(String value, String fieldName) {
        if(!value.matches(NAME_REGEX)) {
            log.error("{} is not in a valid format. Only letters and spaces are allowed !  ",fieldName);
            return true;
        }
        return false;
    }

    public static boolean isNegativeNumber(String value, String fieldName) {
        if(value.charAt(0) == '-') {
            log.error(" {} must not be Negative ",fieldName);
            return true;
        }
        return false;
    }

    public static boolean isInvalidEmailFormat(String value, String fieldName) {
        if(!value.matches(EMAIL_REGEX)) {
            log.error("{} is not a valid email format !  ",fieldName);
            return true;
        }
        return false;
    }
}
