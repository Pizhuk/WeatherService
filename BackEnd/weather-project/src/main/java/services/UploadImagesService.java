package services;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;
import static constants.UploadImagesConstants.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class UploadImagesService {
    public String uploadImages(String inputJSON){
        JSONObject object = new JSONObject(inputJSON);
        String exp = object.getString(EXP_STRING);
        String photo_code = object.getString(PHOTO_CODE_STRING);
        switch (exp){
            case JPG_STRING:
                sendImgToDir(JPG_STRING, photo_code);
                return OK_STRING;
            case PNG_STRING:
                sendImgToDir(PNG_STRING, photo_code);
                return OK_STRING;
            default:
                return ERROR_STRING;
        }
    }

    private void sendImgToDir(String exp, String photo_code){
        FileService fileService = new FileService();
        byte[] resByteArray = Base64.decode(photo_code);
        try {
            BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(resByteArray));
            ImageIO.write(resultImage, exp, new File(DIR_STRING, String.format(FILENAME_STRING, fileService.getFileName(), exp)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
