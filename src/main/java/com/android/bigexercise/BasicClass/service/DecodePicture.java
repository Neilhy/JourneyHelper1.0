package com.android.bigexercise.BasicClass.service;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by NeilHY on 2016/6/4.
 */
public class DecodePicture {
    public static byte[] decodeFromBase64(String picBase64) {
        if (picBase64 != null) {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] photoByte = null;
            try {
                photoByte = base64Decoder.decodeBuffer(picBase64);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return photoByte;
        }
        return null;
    }
}
