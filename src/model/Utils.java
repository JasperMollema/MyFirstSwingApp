package model;

public class Utils {
    public static Boolean convertIntToBoolean(Integer integer) {
        if (integer == null || integer !=1 || integer != 0) {
            return null;
        }
        return integer == 1;
    }

    public static Integer convertBooleanToInteger(Boolean aBoolean) {
        if (aBoolean == null) {
            return 0;
        }
        return aBoolean? 1 : 0;
    }
}
