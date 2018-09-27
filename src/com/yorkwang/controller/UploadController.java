package com.yorkwang.controller;

import java.io.File;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
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
        
        List<UploadFile> file = this.getFiles();
        for (UploadFile uploadFile : file) {
//            if(!(uploadFile.getFileName()).equals(uploadFile.getOriginalFileName())) {
//                File oldFile = new File(path + "/" + uploadFile.getOriginalFileName());
//                if (oldFile.exists()) {
//                    String newName = path + "/" + uploadFile.getFileName();
//                    File newFile = new File(newName);
//                    oldFile.delete();
//                    newFile.renameTo(oldFile);
//                }
//            }
            UploadImageService.addUploadImage(UploadImage.TYPE_COMPANY_INFO, uploadFile.getFileName());
        }
        String res = "{\"code\": 0, \"msg\": \"\"}";
        this.renderJson(res);
    }
    
    public void deletepic() {
        String idString = getPara("id");
        int id = StrKit.isBlank(idString) ? 0 : Integer.parseInt(idString);
        UploadImageService.deleteUploadImage(id, this);
        
        this.renderJson();
    }
    
    public void loadpics() {
        this.setAttr("type", Utils.getIntParaValue(this, "type"));
        String selected_data = getPara("selected_data");
        List<UploadImage> list = UploadImageService.getUploadImages(UploadImage.TYPE_COMPANY_INFO);
        for (UploadImage uploadImage : list) {
            System.out.println("selected_data:" + selected_data);
            System.out.println("path:" + uploadImage.getStr("path"));
            System.out.println("result:" + selected_data.indexOf(uploadImage.getStr("path")));
            if(selected_data.indexOf(uploadImage.getStr("path")) != -1) {
                uploadImage.setSelected(true);
            }
        }
        this.setAttr("pic_list", list);
        
        this.render("pic_list.html");
    }
}
