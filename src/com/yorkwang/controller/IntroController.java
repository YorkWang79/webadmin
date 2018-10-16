package com.yorkwang.controller;

import com.jfinal.core.Controller;
import com.yorkwang.service.SettingService;

public class IntroController extends Controller {
    public void index() {
        String contactor = SettingService.getSetting("contactor");
        String phone = SettingService.getSetting("phone");
        String email = SettingService.getSetting("email");
        String address = SettingService.getSetting("address");
        
        this.setAttr("contactor", contactor);
        this.setAttr("phone", phone);
        this.setAttr("email", email);
        this.setAttr("address", address);
        
        this.render("intro_info.html");
    }
    
    public void save() {
        String contactor = getPara("contactor");
        String phone = getPara("phone");
        String email = getPara("email");
        String address = getPara("address");
        
        SettingService.updateSetting("contactor", contactor);
        SettingService.updateSetting("phone", phone);
        SettingService.updateSetting("email", email);
        SettingService.updateSetting("address", address);
        
        this.renderNull();
    }
}
