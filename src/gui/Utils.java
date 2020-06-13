package gui;

import javax.swing.*;
import java.net.URL;

public class Utils {

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
        URL url = Utils.class.getResource(path);

        if (url == null) {
            System.out.println("Unable to load image " + path);
            return null;
        }

        return new ImageIcon(url);
    }
}
