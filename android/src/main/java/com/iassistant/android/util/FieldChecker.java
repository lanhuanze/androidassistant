package com.iassistant.android.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lan on 11/25/14.
 */
public class FieldChecker {
    public static final boolean isEmail(CharSequence str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Matcher m = VALID_EMAIL.matcher(str);
        boolean matchFound = m.matches();
        return matchFound;
    }

    public static final boolean isPhoneNumber(CharSequence str) {

        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Matcher m = VALID_PHONE_NUMBER.matcher(str);
        return m.matches();
    }

    private final static Pattern VALID_PHONE_NUMBER =
            Pattern.compile("^[0-9.()-]{10,25}$");
    private static final Pattern VALID_EMAIL = Pattern.compile(".+@.+\\.[a-z]+");
}
