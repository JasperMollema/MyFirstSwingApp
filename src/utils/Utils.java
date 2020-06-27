package utils;

import javax.swing.*;
import java.net.URL;

public class Utils {
    private static final String YES = "yes";
    private static final String NO = "no";

    public static Boolean integerToBoolean(Integer integer) {
        if (integer == null || integer !=1 && integer != 0) {
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

    public static String getFileExtension(String name) {

        int pointIndex = name.lastIndexOf(".");

        if (pointIndex == -1) {
            return null;
        }

        if (pointIndex == name.length()-1) {
            return null;
        }

        return name.substring(pointIndex+1);
    }

    public static ImageIcon createIcon(String path) {
        URL url = utils.Utils.class.getResource(path);

        if (url == null) {
            System.out.println("Unable to load image " + path);
            return null;
        }

        return new ImageIcon(url);
    }
}
