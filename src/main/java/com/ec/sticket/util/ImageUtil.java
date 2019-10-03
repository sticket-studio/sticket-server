package com.ec.sticket.util;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
    public static File dataUriToFile(String dataUri){
        File result = new File("img.png");
        byte[] imageData = DatatypeConverter.parseBase64Binary(dataUri.substring(dataUri.indexOf(',') + 1));
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
            ImageIO.write(bufferedImage, "png", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static InputStream dataUriToInputStream(String dataUri){
        byte[] imageData = DatatypeConverter.parseBase64Binary(dataUri.substring(dataUri.indexOf(',') + 1));
        return new ByteArrayInputStream(imageData);
    }
}
