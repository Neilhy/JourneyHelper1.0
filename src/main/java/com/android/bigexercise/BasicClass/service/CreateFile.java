package com.android.bigexercise.BasicClass.service;

import com.android.bigexercise.BasicClass.model.photo;
import com.android.bigexercise.Controller.requestMappingUrl.requestMappingUrl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * Created by NeilHY on 2016/5/29.
 */
public class CreateFile {
    public static File getFile() {
        File rootFile = new File(FileConstant.ROOTFILE);
        if (!rootFile.exists() && !rootFile.isDirectory()) {
            rootFile.mkdir();
        }
        return rootFile;
    }

    public static File getIconFile() {
        File iconFile = new File(getFile().getAbsolutePath() , FileConstant.ICONFILE);
        if (!iconFile.exists() && !iconFile.isDirectory()) {
            iconFile.mkdir();
        }
        return iconFile;
    }

    public static File getPhotoFile() {
        File photoFile = new File(getFile().getAbsolutePath() , FileConstant.PHOTOFILE);
        if (!photoFile.exists() && !photoFile.isDirectory()) {
            photoFile.mkdir();
        }
        return photoFile;
    }
    public static String createIconFilePath(byte[] bytes){
        File iconFile;
        String path;
        do {
             path= getUniqueID.getID().toString() + ".journeypic";
            iconFile= new File(getPhotoFile().getAbsolutePath(), path);
        }while(iconFile.exists());
        try {
            iconFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos=null;
        BufferedOutputStream bos=null;
        try{
            fos = new FileOutputStream(iconFile);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }
    public static Long createPhotoFilePath(byte[] bytes){
        File photoFile;
        Long photoId;
        do {
            photoId= getUniqueID.getID();
            photoFile= new File(getPhotoFile().getAbsolutePath(), photoId.toString()+".journeypic");
        }while(photoFile.exists());
        try {
            photoFile.createNewFile();

//            System.out.println("photoFile in :"+photoFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos=null;
        BufferedOutputStream bos=null;
        try{
            fos = new FileOutputStream(photoFile);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photoId;
    }

    /**
     * 获取图片的本地数据库存储Id。
     *
     * @param photoUrl
     *            图片的URL地址。
     * @return 图片的本地数据库存储Id。
     */
    public static Long getPhotoIdfromUrl(String photoUrl) {
        int lastSlashIndex = photoUrl.lastIndexOf("/");
        String imageName = photoUrl.substring(lastSlashIndex + 1);
        return Long.parseLong(imageName);
    }

    public static String getPhotoUrlfromId(Long photoId) {
        
        return requestMappingUrl.SERVERURL+requestMappingUrl.JOURNEYHELPERPHOTO+"/"+photoId+".journeypic";
//        return requestMappingUrl.LOCALURL+requestMappingUrl.JOURNEYHELPERPHOTO+"/"+photoId+".journeypic";
    }
}
