package com.yorkwang.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.yorkwang.service.DesignService;
import com.yorkwang.utils.Utils;

public class DesignController extends Controller {
    public void list() {
        int year = Utils.getIntParaValue(this, "year");
        
        this.setAttr("designs", DesignService.getDesignByYear(year));
        this.setAttr("year", year);
        
        this.render("design_list.html");
    }
    
    public void info() {
        int year = Utils.getIntParaValue(this, "year");
        String type = getPara("type");
        if(StrKit.notBlank(type) && type.equals("new")) {
        }
        if(StrKit.notBlank(type) && type.equals("edit")) {
            
        }
        this.render("design_info.html");
    }
}
