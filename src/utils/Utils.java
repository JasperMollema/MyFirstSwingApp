package utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
        URL url = createUrl(path);

        if (url == null) {
            return null;
        }

        return new ImageIcon(url);
    }

    public static Font createTrueTypeFont(String path) {
        URL url = createUrl(path);

        if (url == null || !isTrueTypeFont(path)) {
            return null;
        }

        Font font = null;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
        } catch (FontFormatException e) {
            System.err.println("Bad format in font file: " + path);
        } catch (IOException e) {
            System.err.println("Unable to read font file: " + path);
        }

        return font;
    }

    public static boolean isTrueTypeFont(String path) {
        if (path == null) {
            System.err.println("Utils.isTrueTypeFont(): path is null");
            return false;
        }

        String postFix = path.substring(path.length() - 3);
        boolean isTrueTypeFont = postFix.equalsIgnoreCase("ttf");

        if (!isTrueTypeFont) {
            System.err.println("Font file is not an ttf file");
        }

        return isTrueTypeFont;
    }

    public static URL createUrl(String path) {
        URL url = utils.Utils.class.getResource(path);

        if (url == null) {
            System.err.println("Unable to load file " + path);
            return null;
        }

        return url;
    }
}
