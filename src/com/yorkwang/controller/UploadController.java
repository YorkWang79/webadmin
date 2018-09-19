package com.yorkwang.controller;

import java.io.File;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.yorkwang.model.UploadImage;
import com.yorkwang.service.UploadImageService;

public class UploadController extends Controller {
    public void index() {
        this.renderNull();
    }
    
    public void uploadimages() {
        String path = getRequest().getSession().getServletContext().getRealPath("/") + "/upload/";
        
        List<UploadFile> file = this.getFiles();
        for (UploadFile uploadFile : file) {
            if(!(uploadFile.getFileName()).equals(uploadFile.getOriginalFileName())) {
                File oldFile = new File(path + "/" + uploadFile.getOriginalFileName());
                if (oldFile.exists()) {
                    String newName = path + "/" + uploadFile.getFileName();
                    File newFile = new File(newName);
                    oldFile.delete();
                    newFile.renameTo(oldFile);
                }
            }
            UploadImageService.addUploadImage(UploadImage.TYPE_COMPANY_INFO, uploadFile.getOriginalFileName());
        }
        String res = "{\"code\": 0, \"msg\": \"\"}";
        this.renderJson(res);
    }
}
