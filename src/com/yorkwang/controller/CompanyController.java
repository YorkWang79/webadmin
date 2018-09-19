package com.yorkwang.controller;

import com.jfinal.core.Controller;
import com.yorkwang.model.UploadImage;
import com.yorkwang.service.UploadImageService;

public class CompanyController extends Controller {
    public void edit() {
        this.setAttr("company_pics", UploadImageService.getUploadImages(UploadImage.TYPE_COMPANY_INFO));
        
        this.render("company_edit.html");
    }
    
    public void deletepic() {
        this.renderNull();
    }
}
