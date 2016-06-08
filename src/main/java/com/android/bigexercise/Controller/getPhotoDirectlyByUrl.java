package com.android.bigexercise.Controller;

import com.android.bigexercise.BasicClass.service.CreateFile;
import com.android.bigexercise.BasicClass.service.PictureType;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by NeilHY on 2016/6/4.
 */
@RestController
@RequestMapping(value = requestMappingUrl.JOURNEYHELPERPHOTO)
public class getPhotoDirectlyByUrl {
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET, produces = {"image/jpg", "image/png"})
    @ResponseBody
    public byte[] getPhoto(@PathVariable String id) {
        String photoPath = CreateFile.getPhotoFile().getAbsolutePath() + "/" + id;

//        System.out.println("PhotoId:"+id);

        File photoFile = new File(photoPath);

//        System.out.println("PhotoIn:"+photoFile.getAbsolutePath());

        if (photoFile.exists()) {
            byte[] pictureBytes=null;
            String pictureType = getType(photoPath);
            BufferedImage bufferedImage;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                bufferedImage= ImageIO.read(photoFile);
                if (bufferedImage != null) {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage,pictureType , byteArrayOutputStream);
                    pictureBytes = byteArrayOutputStream.toByteArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return pictureBytes;
        }
        return null;
    }

    private String getType(String url)
    {
        if(url==null) return null;

        File file = new File(url);
        if(!file.exists()) return null;
        byte[] b = new byte[4];

        try{
            FileInputStream in = new FileInputStream(file);

            in.read(b, 0, b.length);
            String type = bytesToHexString(b).toUpperCase();
            in.close();

            if (type.contains("FFD8FF")) {
                return PictureType.TYPE_JPG;
            }
            else if (type.contains("89504E47")) {
                return PictureType.TYPE_PNG;
            }
            else if (type.contains("47494638")) {
                return PictureType.TYPE_GIF;
            }
            else if (type.contains("424D")) {
                return PictureType.TYPE_BMP;
            }
            else{
                return PictureType.TYPE_UNKNOWN;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private String bytesToHexString(byte[] src){

        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {

            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }

        return stringBuilder.toString();
    }
}
