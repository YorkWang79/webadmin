package com.yorkwang.service;

import java.io.File;
import java.util.List;

import com.jfinal.core.Controller;
import com.yorkwang.model.UploadImage;

public class UploadImageService {
    
    public static List<UploadImage> getUploadImages(int type) {
        return UploadImage.dao.find("select * from uploadimage where type=" + type);
    }
    
    public static void addUploadImage(int type, String path) {
        UploadImage image = UploadImage.dao.findFirst("select * from uploadimage where type="+type+" and path='" + path + "'");
        if(image == null) {
            image = new UploadImage();
            image.set("type", type);
            image.set("path", path);
            image.save();
//        } else {
//            image.set("type", type);
//            image.set("path", path);
//            image.save();
        }
    }
    
    public static void deleteUploadImage(int id, Controller controller) {
        UploadImage image = UploadImage.dao.findById(id);
        String path = controller.getRequest().getSession().getServletContext().getRealPath("/") + "/upload/" + image.getStr("path");
        new File(path).delete();
        
        UploadImage.dao.deleteById(id);
    }
}
