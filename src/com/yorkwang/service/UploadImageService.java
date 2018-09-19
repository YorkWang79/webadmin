package com.yorkwang.service;

import java.util.List;

import com.yorkwang.model.UploadImage;

public class UploadImageService {
    
    public static List<UploadImage> getUploadImages(int type) {
        return UploadImage.dao.find("select * from uploadimage where type=" + type);
    }
    
    public static void addUploadImage(int type, String path) {
        UploadImage image = new UploadImage();
        image.set("type", type);
        image.set("path", path);
        image.save();
    }
}
