package com.yorkwang.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.yorkwang.model.UploadImage;
import com.yorkwang.utils.Utils;

public class UploadImageService {
    private static HashMap<Integer, UploadImage> pic_map = new HashMap<Integer, UploadImage>();
    static {
        loadCompanyImages();
    }
    
    public static List<UploadImage> getUploadImages() {
        return UploadImage.dao.find("select * from uploadimage");
    }
    
    public static List<UploadImage> getUploadImages(int type) {
        return UploadImage.dao.find("select * from uploadimage where type=" + type);
    }
    
    public static int addUploadImage(int type, String path) {
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
        return image != null ? image.getInt("id") : 0;
    }
    
    public static void deleteUploadImages(int type) {
        UploadImage.dao.find("delete from uploadimage where type=" + type);
    }
    
    public static void deleteUploadImage(int id, Controller controller) {
        UploadImage image = UploadImage.dao.findById(id);
        String path = controller.getRequest().getSession().getServletContext().getRealPath("/") + "/upload/" + image.getStr("path");
        new File(path).delete();
        
        UploadImage.dao.deleteById(id);
    }
    
    public static void loadCompanyImages() {
//        loadCompanyImages(UploadImage.TYPE_COMPANY_INFO);
//    }
    
//    public static void loadCompanyImages(int type) {
        List<UploadImage> list = getUploadImages();
        if(list != null && list.size() != 0) {
            pic_map.clear();
            for (UploadImage uploadImage : list) {
                pic_map.put(uploadImage.getInt("id"), uploadImage);
            }
        }
    }
    
    public static UploadImage getCompanyImage(String id_str) {
        if(StrKit.isBlank(id_str))
            return null;
        return pic_map.get(Integer.parseInt(id_str));
    }
    
    public static UploadImage getCompanyImageId(String path) {
        if(StrKit.isBlank(path))
            return null;
        return UploadImage.dao.findFirst("select * from uploadimage where path='" + path + "'");
    }
    
    public static String[] getCompanyImagesString(String ids) {
        String[] id_arr = ids.split(",");
        String[] path_arr = new String[id_arr.length];
        for (int id=0; id< id_arr.length; id++) {
            UploadImage img = getCompanyImage(id_arr[id]);
            if(img != null) {
                String path = img.getStr("path");
                if(StrKit.notBlank(path))
                    path_arr[id] = path;
            }
        }
        return path_arr;
    }
    
    public static String getCompanyImagesId(String paths) {
        String[] path_arr = paths.split(",");
        Integer[] id_arr = new Integer[path_arr.length];
        for (int i=0; i< path_arr.length; i++) {
            UploadImage img = getCompanyImageId(path_arr[i]);
            if(img != null) {
                id_arr[i] = img.getInt("id");
            }
        }
        return Utils.join(",", id_arr);
    }
}
