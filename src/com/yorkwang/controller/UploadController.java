package com.yorkwang.controller;

import java.io.File;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.yorkwang.model.UploadImage;
import com.yorkwang.service.UploadImageService;
import com.yorkwang.utils.Utils;

public class UploadController extends Controller {
    public void index() {
        this.setAttr("pic_list", UploadImageService.getUploadImages(UploadImage.TYPE_COMPANY_INFO));
        
        this.render("pics.html");
    }
    
    public void uploadimages() {
        String path = getRequest().getSession().getServletContext().getRealPath("/") + "/upload/";
        
        List<UploadFile> files = this.getFiles();
        int type = Utils.getIntParaValue(this, "type");
        type = type == 0 ? UploadImage.TYPE_COMPANY_INFO : type;
        
//        System.out.println("upload files:" + files.size());
        System.out.println("upload type:" + type);
//        if(type == UploadImage.TYPE_COMPANY_INFO)
//            type = UploadImage.TYPE_TEMP;
        
        //Delete the file or files which used. Now comment it out. Maybe it won't used any more.
//        if(files != null && files.size() != 0 && type != UploadImage.TYPE_COMPANY_INFO) {
//            UploadImageService.deleteUploadImages(type);
//        }
        for (UploadFile uploadFile : files) {
            //Overwrite same name file.
            if(!(uploadFile.getFileName()).equals(uploadFile.getOriginalFileName())) {
                File oldFile = new File(path + "/" + uploadFile.getOriginalFileName());
                if (oldFile.exists()) {
                    String newName = path + "/" + uploadFile.getFileName();
                    File newFile = new File(newName);
                    oldFile.delete();
                    newFile.renameTo(oldFile);
                }
            }
            int id = UploadImageService.addUploadImage(type, uploadFile.getOriginalFileName());
        }
        String res = "{\"code\": 0, \"msg\": \"\"}";
        this.renderJson(res);
        
        UploadImageService.loadCompanyImages();
    }
    
    public void deletepic() {
        String idString = getPara("id");
        int id = StrKit.isBlank(idString) ? 0 : Integer.parseInt(idString);
        UploadImageService.deleteUploadImage(id, this);
        
        this.renderJson();
    }
    
    /**
     * Since we want to remove all type images before one new upload.
     * But multiple upload is actually upload file 1 by 1. 
     * We have to use a temp type as -1. When mutiple upload, add with type -1,
     * After all files upload done, delete all old images for this type,
     * then move the -1 type images back to this type.
     */
    public void refactorpics() {
        int type = Utils.getIntParaValue(this, "type");
        type = type == 0 ? UploadImage.TYPE_COMPANY_INFO : type;
        
        UploadImage uploadImage = UploadImage.dao.findFirst("select * from uploadimage where type=-1");
        if(uploadImage != null) {
            Db.update("delete from uploadimage where type=" + type);
            Db.update("update uploadimage set type=" + type + " where type=-1");
        }
        
        this.renderNull();
    }
    
    public void loadpics() {
        int type = Utils.getIntParaValue(this, "type");
        this.setAttr("type", type);
//        String selected_data = getPara("selected_data");
        String path_list = "";
        List<UploadImage> list = UploadImageService.getUploadImages(type);
        for (UploadImage uploadImage : list) {
//            System.out.println("selected_data:" + selected_data);
//            System.out.println("path:" + uploadImage.getStr("path"));
//            if(StrKit.notBlank(selected_data))
//                System.out.println("result:" + selected_data.indexOf(uploadImage.getStr("path")));
//            if(StrKit.notBlank(selected_data) && selected_data.indexOf(uploadImage.getStr("path")) != -1) {
//                uploadImage.setSelected(true);
//            }
            path_list += uploadImage.getStr("id")+",";
        }
        if(StrKit.notBlank(path_list))
            path_list = path_list.substring(0, path_list.length()-1);
        
        this.setAttr("pic_list", list);
        this.setAttr("path_list", path_list);
        this.setAttr("type", type);
        
        this.render("pic_queue.html");
    }
}
