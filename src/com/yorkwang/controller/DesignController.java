package com.yorkwang.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.yorkwang.model.Design;
import com.yorkwang.service.DesignService;
import com.yorkwang.service.UploadImageService;
import com.yorkwang.utils.Utils;

public class DesignController extends Controller {
    public void list() {
        int year = Utils.getIntParaValue(this, "year");
        
        this.setAttr("designs", DesignService.getDesignByYear(year));
        System.out.println("design list:" + DesignService.getDesignByYear(year));
        this.setAttr("year", year);
        
        this.render("design_list.html");
    }
    
    public void info() {
        int year = Utils.getIntParaValue(this, "year");
        String type = getPara("type");
        if(StrKit.notBlank(type) && type.equals("new")) {
        }
        if(StrKit.notBlank(type) && type.equals("edit")) {
            int id = Utils.getIntParaValue(this, "id");
            Design design = Design.dao.findById(id);
            design.generatePaths();
            this.setAttr("design", design);
        }
        this.setAttr("year", year);
        
        this.render("design_info.html");
    }
    
    public void create() {
        int year = Utils.getIntParaValue(this, "year");
        String title = getPara("design_title");
        String desc = getPara("design_desc");
        String files = UploadImageService.getCompanyImagesId(getPara("design_pic_id1"));
        
        Design design = new Design();
        design.set("year", year);
        design.set("title", title);
        design.set("desc", desc);
        design.set("pic_ids", files);
        design.save();
        
        this.renderNull();
    }
    
    public void edit() {
        int year = Utils.getIntParaValue(this, "year");
        int id = Utils.getIntParaValue(this, "design_id");
        String title = getPara("design_title");
        String desc = getPara("design_desc");
        String files = UploadImageService.getCompanyImagesId(getPara("design_pic_id1"));
        
        Design design = Design.dao.findById(id);
        if(design != null) {
            design.set("year", year);
            design.set("title", title);
            design.set("desc", desc);
            if(StrKit.notBlank(files))
                design.set("pic_ids", files);
            design.update();
        }
        
        this.renderNull();
    }
    
    public void delete() {
        int id = Utils.getIntParaValue(this, "id");
        DesignService.delete(id);
        
        this.renderNull();
    }
}
