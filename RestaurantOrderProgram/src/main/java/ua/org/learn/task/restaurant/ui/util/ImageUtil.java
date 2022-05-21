package ua.org.learn.task.restaurant.ui.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static Image getImage(String imagePath) {
        File iconFile = new File(ClassLoader.getSystemClassLoader().getResource(imagePath).getPath());
        if (iconFile.exists()) {
            try {
                return ImageIO.read(iconFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Icon getIcon(String iconPath) {
        Image image = getImage(iconPath);
        if (image != null) {
            return new ImageIcon(image);
        }
        return null;
    }
}
