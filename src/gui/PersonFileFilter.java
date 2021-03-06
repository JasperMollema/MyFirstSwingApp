package gui;

import utils.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        String name = Utils.getFileExtension(file.getName());
        return file.isDirectory() || name !=null && name.equals("per");
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
