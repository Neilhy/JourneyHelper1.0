package com.android.bigexercise.BasicClass.service;

import java.util.Random;

/**
 * Created by NeilHY on 2016/5/28.
 */
public class getUniqueID {
    public static Long getID() {
        Random random=new Random();
        int tmp=random.nextInt(1000)+1;
        String systemTime = String.valueOf(System.currentTimeMillis())+tmp;
        return Long.parseLong(systemTime);
    }
}
