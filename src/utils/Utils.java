package utils;

public class Utils {
    private static final String YES = "yes";
    private static final String NO = "no";

    public static Boolean integerToBoolean(Integer integer) {
        if (integer == null || integer !=1 || integer != 0) {
            return null;
        }
        return integer == 1;
    }

    public static Integer booleanToInteger(Boolean aBoolean) {
        if (aBoolean == null) {
            return 0;
        }
        return aBoolean? 1 : 0;
    }

    public static String booleanToString(Boolean aBoolean) {
        if (aBoolean != null && aBoolean) {
            return YES;
        }
        else {
            return NO;
        }
    }

    public static Boolean stringToBoolean(String string) {
        if (string.equals(YES)) {
            return Boolean.FALSE;
        }
        else {
            return Boolean.TRUE;
        }
    }
}
