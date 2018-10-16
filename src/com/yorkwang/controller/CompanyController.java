package com.yorkwang.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.yorkwang.model.Company;
import com.yorkwang.model.UploadImage;
import com.yorkwang.service.UploadImageService;
import com.yorkwang.utils.Utils;

public class CompanyController extends Controller {
    
    public void edit() {
        Company company = Company.dao.findFirst("select * from company");

        if(company != null) {
            this.setAttr("name", company.getStr("name"));
            this.setAttr("desc", company.getStr("desc"));
            String[] pics = UploadImageService.getCompanyImagesString(company.getStr("pics"));
            this.setAttr("company_pics", pics);
            String picsStr = Utils.getArrayString(pics);
            this.setAttr("company_pics_path", picsStr);
            UploadImage pic1 = UploadImageService.getCompanyImage(company.getStr("team_pic1"));
//            System.out.println("pic1 id:" + company.getStr("team_pic1"));
//            System.out.println("pic1:" + pic1);
            if(pic1 != null)
                this.setAttr("pic1", pic1.get("path"));
            UploadImage pic2 = UploadImageService.getCompanyImage(company.getStr("team_pic2"));
            if(pic2 != null)
                this.setAttr("pic2", pic2.get("path"));
            UploadImage pic3 = UploadImageService.getCompanyImage(company.getStr("team_pic3"));
            if(pic3 != null)
                this.setAttr("pic3", pic3.get("path"));
        }
        
        this.render("company_edit.html");
    }
    
    public void save() {
        String name = getPara("company_name");
        String desc = getPara("desc");
        String pics = UploadImageService.getCompanyImagesId(getPara("company_pic_id1"));
        String pic1 = UploadImageService.getCompanyImagesId(getPara("company_pic_id2"));
        String pic2 = UploadImageService.getCompanyImagesId(getPara("company_pic_id3"));
        String pic3 = UploadImageService.getCompanyImagesId(getPara("company_pic_id4"));
        
        boolean result = false;
        Company company = Company.dao.findById(name);
        if (company == null) {
            company = new Company();
            company.set("name", name);
            company.set("desc", desc);
            company.set("pics", pics);
            company.set("team_pic1", pic1);
            company.set("team_pic2", pic2);
            company.set("team_pic3", pic3);
            result = company.save();
        } else {
            company.set("name", name);
            if(StrKit.notBlank(desc))
                company.set("desc", desc);
            if(StrKit.notBlank(pics))
                company.set("pics", pics);
            if(StrKit.notBlank(pic1))
                company.set("team_pic1", pic1);
            if(StrKit.notBlank(pic2))
                company.set("team_pic2", pic2);
            if(StrKit.notBlank(pic3))
                company.set("team_pic3", pic3);
            result = company.update();
        }
        this.setAttr("error", result);
        
        this.render("company_edit.html");
    }
}
