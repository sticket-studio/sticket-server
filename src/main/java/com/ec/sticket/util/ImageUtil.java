package com.ec.sticket.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;

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

    public static File convertMultiPartToFile(MultipartFile multipartFile){

        File convFile = new File(multipartFile.getOriginalFilename());
        try{
            FileOutputStream fos= new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }
}
