package com.yorkwang.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.yorkwang.service.DesignService;

public class AdminPageController extends Controller {
    public void index() {
        List<Integer> years = DesignService.getDesignYears();
        
        this.setAttr("years", years);
        
        this.render("admin_page.html");
    }
}
